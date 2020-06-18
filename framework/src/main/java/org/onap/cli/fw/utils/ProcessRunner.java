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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;

public class ProcessRunner {
    private static Logger log = LoggerFactory.getLogger(ProcessRunner.class);
    public static final String WIN_SHELL = "cmd.exe /c ";
    public static final String UNIX_SHELL = "";
    private String []cmd = null;
    private String shell = System.getProperty("os.name").toLowerCase().startsWith("windows") ? WIN_SHELL : UNIX_SHELL;
    private String cwd = System.getProperty("user.home");
    private String []env = null;
    private int exitCode = -1;
    private String output = "";
    private String error = "";
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

        File workingDirectory = null;
        if (cwd != null) {
           workingDirectory = new File(cwd);
        }
        if (this.cmd.length == 1) {
            p = Runtime.getRuntime().exec(this.shell + this.cmd[0], this.env, workingDirectory); //NOSONAR
        } else {
            List<String> list = new ArrayList<>(Arrays.asList(this.shell.split(" ")));
            list.addAll(Arrays.asList(this.cmd));
            String []cmds = Arrays.copyOf(list.toArray(), list.size(), String[].class);
            p = Runtime.getRuntime().exec(cmds, this.env, workingDirectory); //NOSONAR
        }

        boolean readOutput = false;
        if (this.getStdout() == null) {
            this.setStdout(new ByteArrayOutputStream());
            readOutput = true;
        }

        boolean readError = false;
        if (this.getStderr() == null) {
            this.setStderr(new ByteArrayOutputStream());
            readError = true;
        }

        final OutputStream stdout = this.getStdout();
        final OutputStream stderr = this.getStderr();

        final InputStream stdoutP = p.getInputStream();
        final InputStream stderrP = p.getErrorStream();

        Thread outThread = new Thread(new Runnable() {
            public void run() {
                try {
                    IOUtils.copy(stdoutP, stdout);
                } catch (IOException e) { // NOSONAR
                }
            }
        });

        Thread errThread = new Thread(new Runnable() {
            public void run() {
                try {
                    IOUtils.copy(stderrP, stderr);
                } catch (IOException e) { // NOSONAR
                }
            }
        });

        outThread.start();
        errThread.start();

        boolean completed = p.waitFor(this.getTimeout(), TimeUnit.MILLISECONDS);
        outThread.join();
        errThread.join();

        if (completed) {
            this.exitCode = p.exitValue();
        }

        if (readOutput)
            this.output = new String(((ByteArrayOutputStream)this.getStdout()).toByteArray(), StandardCharsets.UTF_8);

        if (readError)
            this.error = new String(((ByteArrayOutputStream)this.getStderr()).toByteArray(), StandardCharsets.UTF_8);

        p.destroy();

        log.debug("CMD: {} \nWORKING_DIR: {} \nENV: {} \nOUTPUT: {} \nERROR: {} \nEXIT_CODE: {}",
                Arrays.asList(this.cmd), this.cwd, ((this.env == null) ? this.env : Arrays.asList(this.env)), this.output, this.error, this.exitCode);

        if (!completed) {
            throw new RuntimeException("TIMEOUT:: cmd:" + Arrays.asList(this.cmd).toString());
        }
    }

    public String streamToString(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
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