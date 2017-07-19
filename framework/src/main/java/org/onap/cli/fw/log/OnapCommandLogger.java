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

package org.onap.cli.fw.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helps to log the command method boundary calls.
 *
 */
@Aspect
public class OnapCommandLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnapCommandLogger.class);

    //(mrkanag) verify that it logs for all classes in this project.
    /**
     * Logging intercepter.
     *
     * @param joinPoint
     *            joinpoint
     * @return object
     * @throws Throwable
     *             exception
     */
    @Around("execution(* org.onap.cli.fw*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable { // NOSONAR
        LOGGER.info(joinPoint.getThis().toString() + "->" + joinPoint.getSignature().getName() + "("
                + joinPoint.getArgs() + ")");

        Object response = joinPoint.proceed();
        LOGGER.info(response.toString());

        return response;
    }
}
