/*
 * This file is part of TicTaaC.
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
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.util;

import java.util.*;
import java.util.stream.Stream;

public class ConsoleUtil {

    private final static List<String> PARAMS_WHITELIST = Arrays.asList(new String[]{
            "-h", "help", "threatModel", "out", "outFormat", "mitigations",
            "failOnThreatRisk", "threatsLibrary", "threatsLibraryAccessUsername",
            "threatsLibraryAccessPassword", "-v", "version"
    });

    private ConsoleUtil() {
    }

    private static class ConsoleParam {
        private String key;
        private List<String> values = new ArrayList<>();

        public ConsoleParam(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValues() {
            return values;
        }

        public void addValue(String value) {
            this.values.add(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ConsoleParam that = (ConsoleParam) o;
            return Objects.equals(key, that.key) && Objects.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, values);
        }
    }

    public static Map<String, List<String>> getParamsMap(String[] args) {
        Stack<ConsoleParam> params = new Stack<>();
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].trim().startsWith("--") ||
                        args[i].trim().startsWith("-v") || args[i].trim().startsWith("-h")) {
                    params.push(new ConsoleParam(args[i].trim()
                            .replaceFirst("--", "")));
                } else {
                    if (!params.empty()) {
                        params.peek().addValue(
                                args[i].trim().replaceAll("\"", "")
                                        .replaceAll("'", ""));
                    }
                }
            }
        }

        Map<String, List<String>> paramsMap = new HashMap<>();
        while (!params.empty()) {
            ConsoleParam param = params.pop();
            paramsMap.put(param.getKey(), param.getValues());
        }

        return paramsMap;
    }

    public static boolean hasOnlyAllowed(Map<String, List<String>> params) {
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            if (!PARAMS_WHITELIST.stream()
                    .anyMatch(entry.getKey()::equalsIgnoreCase)) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, String> copySingleValueParamsWithDefinedArg(Map<String, List<String>> params,
                                                                          String paramName, String paramValue) {
        Map<String, String> singleValueParams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                String value = paramName.equalsIgnoreCase(entry.getKey()) ? paramValue : entry.getValue().get(0);
                singleValueParams.put(entry.getKey(), value);
            }
        }
        return singleValueParams;
    }

}
