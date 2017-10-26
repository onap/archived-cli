/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.onap.cli.sample.yaml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SampleYamlGenerator {

    static int nTab;

    public static void generateSampleYaml(List<String> input, String ouput, String version,
            String targetPath, boolean debug) throws IOException {

        String cmdName = input.get(0);

        PrintWriter writer = new PrintWriter(targetPath, "UTF-8");
        writeKeyValuePair(writer, "onap_cli_sample_version", "1.0");
        writeKeyValuePair(writer, "name", cmdName);
        writeKeyValuePair(writer, "version", version);

        writeKey(writer, "samples");
        writeKey(writer, "sample1");

        writeKeyValuePair(writer, "name", cmdName);
        writeKeyValuePair(writer, "input", input.stream().skip(1).collect(Collectors.joining(" ")));
        writeKeyValuePair(writer, "moco", new File(targetPath).getName().replaceAll("-sample.yaml", "-moco.json"));
        writeMultilineKeyValue(writer, "ouput", ouput, debug);

        writeEndKey();
        writeEndKey();

        writer.flush();
        writer.close();
    }

    private static void writeMultilineKeyValue(PrintWriter writer, String key, String value, boolean debug) {
        writer.write(printTabs() + key + ": |\n");
        nTab++;
        String[] lines = value.split("\n");
        long skipLines = debug ? 12 : 0;
        Arrays.stream(lines).skip(skipLines ).forEach(line -> writer.write(printTabs() + line + "\n")); // NOSONAR
    }

    private static String printTabs() {
        StringBuffer spaces = new StringBuffer();
        for (int i=0; i < nTab; i++) {
            spaces.append("  ");
        }
        return spaces.toString();
    }

    private static void writeKeyValuePair(PrintWriter writer, String key, String value) {
        writer.write(printTabs() +key + ": " + value + "\n");
    }

    private static void writeKey(PrintWriter writer, String key) {
        writer.write(printTabs() + key + ":\n");
        nTab++;
    }

    private static void writeEndKey() {
        nTab--;
    }
}
