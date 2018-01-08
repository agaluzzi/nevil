/*
 * Copyright 2018 Aaron Galuzzi
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

package galuzzi.codegen.java.support

import galuzzi.codegen.java.JavaField
import galuzzi.codegen.java.Scope
import galuzzi.codegen.java.Type

/**
 * TODO...
 */
interface FieldHolder
{
    fun getFields(): List<JavaField>

    fun field(name: String,
              type: Type,
              scope: Scope = Scope.PRIVATE,
              nullable: Boolean = true,
              description: String = "",
              init: JavaField.() -> Unit = {}): JavaField

    class Impl : FieldHolder
    {
        private val fields = mutableListOf<JavaField>()

        override fun getFields(): List<JavaField>
        {
            return fields
        }

        override fun field(name: String, type: Type, scope: Scope, nullable: Boolean, description: String, init: JavaField.() -> Unit): JavaField
        {
            val field = JavaField(scope, type, name, nullable, description).apply(init)
            fields += field
            return field
        }
    }
}