/*
 * Copyright 2016-17 Huawei Technologies Co., Ltd.
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

package org.onap.cli.fw.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;
import org.junit.Test;

public class OnapCommandLoggerTest {

    @Test
    public void logTest() throws Throwable {


        ProceedingJoinPoint point = new ProceedingJoinPoint() {
            @Override
            public String toShortString() {
                return null;
            }

            @Override
            public String toLongString() {
                return null;
            }

            @Override
            public Object getThis() {
                return new Object();
            }

            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public StaticPart getStaticPart() {
                return null;
            }

            @Override
            public SourceLocation getSourceLocation() {
                return null;
            }

            @Override
            public Signature getSignature() {
                return new Signature(){

                    @Override
                    public Class getDeclaringType() {
                        // TODO Auto-generated method stub
                        return null;
                    }

                    @Override
                    public String getDeclaringTypeName() {
                        // TODO Auto-generated method stub
                        return null;
                    }

                    @Override
                    public int getModifiers() {
                        // TODO Auto-generated method stub
                        return 0;
                    }

                    @Override
                    public String getName() {
                        // TODO Auto-generated method stub
                        return null;
                    }

                    @Override
                    public String toLongString() {
                        // TODO Auto-generated method stub
                        return null;
                    }

                    @Override
                    public String toShortString() {
                        // TODO Auto-generated method stub
                        return null;
                    }

                };
            }

            @Override
            public String getKind() {
                return null;
            }

            @Override
            public Object[] getArgs() {
                return null;
            }

            @Override
            public void set$AroundClosure(AroundClosure arg0) {
            }

            @Override
            public Object proceed(Object[] arg0) throws Throwable {
                return null;
            }

            @Override
            public Object proceed() throws Throwable {
                return new Object();
            }
        };

        OnapCommandLogger log = new OnapCommandLogger();
        log.log(point);
    }

}
