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
    private SampleYamlGenerator() {
        throw new IllegalStateException("Utility class");
    }
    public static void generateSampleYaml(String cmdName, List<String> input, String output, String version,
            String targetPath, boolean debug, String name) throws IOException {

        PrintWriter writer = new PrintWriter(targetPath, "UTF-8");
        writeKeyValuePair(writer, "open_cli_sample_version", "1.0");
        writeKeyValuePair(writer, "name", cmdName);
        writeKeyValuePair(writer, "version", version);

        if (name == null) {
            name = "sample1";
        }
        writeKey(writer, "samples");
        writeKey(writer, name);

        writeKeyValuePair(writer, "name", cmdName);
        writeKeyValuePair(writer, "input", input.stream().collect(Collectors.joining(" ")).trim());
        writeKeyValuePair(writer, "moco", new File(targetPath).getName().replaceAll("-sample.yaml", "-moco.json"));
        writeMultilineKeyValue(writer, "output", output.trim(), debug);

        writeEndKey();
        writeEndKey();

        writer.flush();
        writer.close();
    }

    private static void writeMultilineKeyValue(PrintWriter writer, String key, String value, boolean debug) { //NOSONAR
        writer.write(printTabs() + key + ":");
        if (value.isEmpty()) {
            return;
        }
        writer.write(" |\n");
        nTab++;
        String[] lines = value.split("\n");
        Arrays.stream(lines).forEach(line -> writer.write(printTabs() + line + "\n")); // NOSONAR
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
