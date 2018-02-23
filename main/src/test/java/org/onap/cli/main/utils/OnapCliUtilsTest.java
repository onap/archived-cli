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

package org.onap.cli.main.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.onap.cli.fw.error.OnapCommandException;
import org.onap.cli.fw.input.OnapCommandParameter;
import org.onap.cli.fw.input.OnapCommandParameterType;
import org.onap.cli.main.error.OnapCliArgumentValueMissing;
import org.onap.cli.main.error.OnapCliInvalidArgument;

public class OnapCliUtilsTest {

    @Test
    public void testpopulateParamsLong() throws OnapCommandException {
        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setLongOption("host-username");
        param1.setName("host-username");
        param1.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param2 = new OnapCommandParameter();
        param2.setLongOption("host-password");
        param2.setName("host-password");
        param2.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param3 = new OnapCommandParameter();
        param3.setLongOption("host-url");
        param3.setName("host-url");
        param3.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param4 = new OnapCommandParameter();
        param4.setLongOption("string-param");
        param4.setName("string-param");
        param4.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param5 = new OnapCommandParameter();
        param5.setLongOption("long-opt");
        param5.setName("long-opt");
        param5.setParameterType(OnapCommandParameterType.STRING);

        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(param1);
        paramslist.add(param2);
        paramslist.add(param3);
        paramslist.add(param4);
        paramslist.add(param5);

        String[] args = new String[] { "sample-create", "--host-username", "admin", "--host-password", "123",
                "--host-url", "a@b.com", "--string-param", "blah", "--long-opt", "10" };
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);

        Assert.assertEquals("host-username", expectedList.get(2), param1.getValue());
        Assert.assertEquals("host-password", expectedList.get(4), param2.getValue());
        Assert.assertEquals("host-url", expectedList.get(6), param3.getValue());
        Assert.assertEquals("string-param", expectedList.get(8), param4.getValue());
        Assert.assertEquals("long-opt", expectedList.get(10), param5.getValue());

    }

    @Test
    public void testpositionalargs() throws OnapCommandException {
        OnapCommandParameter paramargs = new OnapCommandParameter();
        paramargs.setName("http://localhost:8082/file.txt");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(paramargs);
        String[] args = new String[] { "positional-args", "http://localhost:8082/file.txt" };
        paramargs.setParameterType(OnapCommandParameterType.STRING);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertEquals("positional-args", expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test
    public void testboolparamslong() throws OnapCommandException {
        OnapCommandParameter boolparam = new OnapCommandParameter();
        boolparam.setLongOption("bool");
        boolparam.setName("bool-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(boolparam);
        String[] args = new String[] { "sample-create", "--bool" };

        boolparam.setParameterType(OnapCommandParameterType.BOOL);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());

    }

    @Test
    public void testboolparamsshort() throws OnapCommandException {
        OnapCommandParameter boolparam = new OnapCommandParameter();
        boolparam.setShortOption("b");
        boolparam.setName("bool-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(boolparam);
        String[] args = new String[] { "sample-create", "-b", };

        boolparam.setParameterType(OnapCommandParameterType.BOOL);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test
    public void testTextparamslong() throws OnapCommandException {
        OnapCommandParameter boolparam = new OnapCommandParameter();
        boolparam.setLongOption("text-param");
        boolparam.setName("text-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(boolparam);
        String[] args = new String[] { "sample-create", "--text-param" , "text"};

        boolparam.setParameterType(OnapCommandParameterType.TEXT);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());

    }

    @Test
    public void testTextparamsshort() throws OnapCommandException {
        OnapCommandParameter boolparam = new OnapCommandParameter();
        boolparam.setShortOption("e");
        boolparam.setName("text-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(boolparam);
        String[] args = new String[] { "sample-create", "-e" , "text"};

        boolparam.setParameterType(OnapCommandParameterType.TEXT);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test
    public void testjsonparamsshort() throws OnapCommandException {
        OnapCommandParameter jsonparam = new OnapCommandParameter();
        jsonparam.setShortOption("j");
        jsonparam.setName("json-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(jsonparam);
        File resourcesDirectory = new File("src/test/resources/sampletest.json");
        String[] args = new String[] { "sample-create", "-j", "file:" + resourcesDirectory };
        jsonparam.setParameterType(OnapCommandParameterType.JSON);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test
    public void testjsonparamslong() throws OnapCommandException {
        OnapCommandParameter jsonparam = new OnapCommandParameter();
        jsonparam.setLongOption("json-param");
        jsonparam.setName("json-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(jsonparam);
        File resourcesDirectory = new File("src/test/resources/sampletest.json");
        String[] args = new String[] { "sample-create", "--json-param", "file:" + resourcesDirectory };
        jsonparam.setParameterType(OnapCommandParameterType.JSON);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test
    public void testpopulateParamsShort() throws OnapCommandException {

        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setShortOption("u");
        param1.setName("host-username");
        param1.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param2 = new OnapCommandParameter();
        param2.setShortOption("p");
        param2.setName("host-password");
        param2.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param3 = new OnapCommandParameter();
        param3.setShortOption("r");
        param3.setName("host-url");
        param3.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param4 = new OnapCommandParameter();
        param4.setShortOption("c");
        param4.setName("string-param");
        param4.setParameterType(OnapCommandParameterType.STRING);
        OnapCommandParameter param5 = new OnapCommandParameter();
        param5.setShortOption("l");
        param5.setName("long-opt");
        param5.setParameterType(OnapCommandParameterType.STRING);

        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(param1);
        paramslist.add(param2);
        paramslist.add(param3);
        paramslist.add(param4);
        paramslist.add(param5);

        String[] args11 = new String[] { "sample-create", "-u", "admin", "-p", "123", "-r", "a@b.com", "-c", "blah",
                "-l", "10", };
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args11));

        List<String> expectedList = Arrays.asList(args11);

        Assert.assertEquals("u", expectedList.get(2), param1.getValue());
        Assert.assertEquals("-p", expectedList.get(4), param2.getValue());
        Assert.assertEquals("r", expectedList.get(6), param3.getValue());
        Assert.assertEquals("c", expectedList.get(8), param4.getValue());
        Assert.assertEquals("l", expectedList.get(10), param5.getValue());
    }

    @Test
    public void testArrayparamslong() throws OnapCommandException {
        OnapCommandParameter arrayval = new OnapCommandParameter();
        arrayval.setLongOption("node-ip");
        arrayval.setName("node-ip");

        String[] args = new String[] { "sample-create", "--node-ip", "{}" };
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(arrayval);

        arrayval.setParameterType(OnapCommandParameterType.ARRAY);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));

        List<String> expectedList = Arrays.asList(args);
        Assert.assertNotNull(expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test
    public void testMapparamsShort() throws OnapCommandException {
        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setLongOption("map");
        param1.setName("MAP");
        param1.setParameterType(OnapCommandParameterType.MAP);
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(param1);

        param1.setParameterType(OnapCommandParameterType.MAP);
        OnapCliArgsParser.populateParams(paramslist,
                Arrays.asList("show", "--map", "param1=value1", "--map", "param2=value2"));

        Assert.assertEquals("{param1=value1, param2=value2}", paramslist.iterator().next().getValue().toString());
    }

    @Test(expected = OnapCliInvalidArgument.class)
    public void testMapparamsLongfail() throws OnapCommandException {
        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setLongOption("map");
        param1.setName("MAP");
        param1.setParameterType(OnapCommandParameterType.MAP);
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(param1);

        param1.setParameterType(OnapCommandParameterType.MAP);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList("show", "--map", "param1=value1", "--map", "param2"));
        Assert.assertEquals("{\"param1\":\"value1\",\"param2\"}", paramslist.iterator().next().getValue().toString());
    }

    @Test(expected = OnapCliInvalidArgument.class)
    public void testMapparamsShortfail() throws OnapCommandException {
        OnapCommandParameter param1 = new OnapCommandParameter();
        param1.setShortOption("o");
        param1.setName("node-port");
        param1.setParameterType(OnapCommandParameterType.MAP);
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(param1);
        param1.setParameterType(OnapCommandParameterType.MAP);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList("show", "-o", "param1=value1", "-o", "param2"));
        Assert.assertEquals("{\"param1\":\"value1\",\"param2\"}", paramslist.iterator().next().getValue().toString());
    }

    @Test(expected = OnapCliInvalidArgument.class)
    public void testpositionalargsfails() throws OnapCommandException {
        OnapCommandParameter paramargs = new OnapCommandParameter();
        paramargs.setName("http://localhost:8082/file.txt");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(paramargs);
        String[] args = new String[] { "positional-args", "http://localhost:8082/file.txt",
                "http://localhost:8082/file.txt" };
        paramargs.setParameterType(OnapCommandParameterType.STRING);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertEquals("positional-args", expectedList.get(1), paramslist.iterator().next().getValue());
    }

    @Test(expected = OnapCliInvalidArgument.class)
    public void testboolparamsshortfails() throws OnapCommandException {
        OnapCommandParameter boolparam = new OnapCommandParameter();
        boolparam.setShortOption("b");
        boolparam.setName("bool-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(boolparam);
        String[] args = new String[] { "sample-create", "-b", "-b", "-h" };

        boolparam.setParameterType(OnapCommandParameterType.BOOL);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertEquals("true", paramslist.iterator().next().getValue());
    }

    @Test(expected = OnapCliInvalidArgument.class)
    public void testboolparamsLongfails() throws OnapCommandException {
        OnapCommandParameter boolparam = new OnapCommandParameter();
        boolparam.setShortOption("bool");
        boolparam.setName("bool-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(boolparam);
        String[] args = new String[] { "sample-create", "--bool", "--bool", "--help" };

        boolparam.setParameterType(OnapCommandParameterType.BOOL);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertEquals("true", paramslist.iterator().next().getValue());
    }

    @Test(expected = OnapCliArgumentValueMissing.class)
    public void testjsonparamslongfails() throws OnapCommandException {
        OnapCommandParameter jsonparam = new OnapCommandParameter();
        jsonparam.setLongOption("json-param");
        jsonparam.setName("json-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(jsonparam);
        File resourcesDirectory = new File("src/test/resources/sampletest.json");
        String[] args = new String[] { "sample-create", "--json-param", "file:" + resourcesDirectory, "--json-param" };
        jsonparam.setParameterType(OnapCommandParameterType.JSON);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertEquals("--json-param", paramslist.iterator().next().getValue());

    }

    @Test(expected = OnapCliArgumentValueMissing.class)
    public void testjsonparamsshortfails() throws OnapCommandException {
        OnapCommandParameter jsonparam = new OnapCommandParameter();
        jsonparam.setShortOption("j");
        jsonparam.setName("json-param");
        Set<OnapCommandParameter> paramslist = new HashSet<>();
        paramslist.add(jsonparam);
        File resourcesDirectory = new File("src/test/resources/sampletest.json");
        String[] args = new String[] { "sample-create", "-j", "file:" + resourcesDirectory, "-j" };
        jsonparam.setParameterType(OnapCommandParameterType.JSON);
        OnapCliArgsParser.populateParams(paramslist, Arrays.asList(args));
        List<String> expectedList = Arrays.asList(args);
        Assert.assertEquals("--json-param", paramslist.iterator().next().getValue());

    }
}