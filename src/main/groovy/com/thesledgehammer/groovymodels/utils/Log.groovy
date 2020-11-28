/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thesledgehammer.groovymodels.utils

import com.thesledgehammer.groovymodels.GroovyModels
import net.minecraft.item.ItemStack
import org.apache.logging.log4j.Level

class Log {

    static void log(Level logLevel, String message) {
        GroovyModels.LOGGER.log(logLevel, message);
    }

    static void log(Level logLevel, String message, Object e) {
        GroovyModels.LOGGER.log(logLevel, message, e);
    }

    static void logInfo(String message){
        GroovyModels.LOGGER.info(message);
    }

    static void logWarn(String message) {
        GroovyModels.LOGGER.warn(message);
    }

    static void logFatal(String message) {
        GroovyModels.LOGGER.fatal(message);
    }

    static void logDebug(String message) {
        GroovyModels.LOGGER.debug(message);
    }

    static void logError(String string) {
        GroovyModels.LOGGER.error(string);
    }

    static void logError(String string, ItemStack stack) {
        GroovyModels.LOGGER.error(string, stack);
    }
}
