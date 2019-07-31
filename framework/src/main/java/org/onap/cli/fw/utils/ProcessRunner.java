/*
 * Copyright 2018 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onap.cli.fw.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessRunner {
    private static Logger log = LoggerFactory.getLogger(ProcessRunner.class);
    public static final String WIN_SHELL = "cmd.exe /c ";
    public static final String UNIX_SHELL = "";
    private String []cmd = null;
    private String shell = System.getProperty("os.name").toLowerCase().startsWith("windows") ? WIN_SHELL : UNIX_SHELL;
    private String cwd = System.getProperty("user.home");
    private String []env = null;
    private int exitCode = -1;
    private String output;
    private String error;
    private long timeout = 0;
    private OutputStream stdout;
    private OutputStream stderr;
    public ProcessRunner(String []cmd, String []env, String cwd) {
        this.cmd = cmd;

        if (cwd != null && !cwd.isEmpty()) {
            this.cwd = cwd;
        }

        this.env = env;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void overrideToUnix() {
        this.shell = UNIX_SHELL;
    }

    public ProcessRunner(String []cmd, String cwd) {
        this(cmd, null, cwd);
    }

    public ProcessRunner(String []cmd) {
        this(cmd, null, null);
    }

    public ProcessRunner(String cmd, String []env, String cwd) {
        this(new String []{cmd}, env, cwd);
    }

    public ProcessRunner(String cmd, String cwd) {
        this(new String []{cmd}, null, cwd);
    }

    public ProcessRunner(String cmd) {
        this(new String []{cmd}, null, null);
    }

    @SuppressWarnings("unchecked")
    public void run() throws InterruptedException, IOException {
        Process p = null;

        final StringWriter writerOutput = new StringWriter();
        final StringWriter writerError = new StringWriter();

        final OutputStream stdout = this.getStdout();
        final OutputStream stderr = this.getStderr();

        if (this.cmd.length == 1) {
            p = Runtime.getRuntime().exec(this.shell + this.cmd[0], this.env, null);
        } else {
            List list = new ArrayList(Arrays.asList(this.shell.split(" ")));
            list.addAll(Arrays.asList(this.cmd));
            String []cmds = Arrays.copyOf(list.toArray(), list.size(), String[].class);
            p = Runtime.getRuntime().exec(cmds, this.env, null);
        }

        final Process p1 = p;
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (stdout != null) {
                        IOUtils.copy(p1.getInputStream(), stdout);
                    }
                    else IOUtils.copy(p1.getInputStream(), writerOutput);
                } catch (IOException e) {
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    if (stderr != null) {
                        IOUtils.copy(p1.getErrorStream(), stderr);
                    }
                    else IOUtils.copy(p1.getErrorStream(), writerError);
                } catch (IOException e) {
                }
            }
        }).start();

        boolean completed = p.waitFor(this.getTimeout(), TimeUnit.MILLISECONDS);
        if (completed) {
            this.exitCode = p.exitValue();
        }

        this.output = writerOutput.toString();
        this.error = writerError.toString();
        log.debug("CMD: " + Arrays.asList(this.cmd).toString() + "\nWORKING_DIR: " + this.cwd + "\nENV: " +
        ((this.env == null) ? this.env : Arrays.asList(this.env).toString()) +
                "\nOUTPUT: " + this.output + "\nERROR: " + this.error + "\nEXIT_CODE: " + this.exitCode);
        p.destroy();

        if (!completed) {
            throw new RuntimeException("TIMEOUT:: cmd:" + Arrays.asList(this.cmd).toString());
        } else {

        }
    }

    public String streamToString(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return sb.toString();
    }

    public int getExitCode() {
        return this.exitCode;
    }

    public String getOutput() {
        return this.output;
    }

    public String getError() {
        return this.error;
    }

    public OutputStream getStdout() {
        return stdout;
    }

    public void setStdout(OutputStream stdout) {
        this.stdout = stdout;
    }

    public OutputStream getStderr() {
        return stderr;
    }

    public void setStderr(OutputStream stderr) {
        this.stderr = stderr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("COMMAND: " + this.shell + " " + Arrays.asList(this.cmd));
        sb.append("\nCWD: " + new File(this.cwd).getAbsolutePath());
        sb.append("\nTIMEOUT: " + this.timeout);
        sb.append("\nEXIT-CODE: " + this.getExitCode());
        sb.append("\nENVIRONMENTS: " + Arrays.asList(this.env));

        return sb.toString();
    }
}