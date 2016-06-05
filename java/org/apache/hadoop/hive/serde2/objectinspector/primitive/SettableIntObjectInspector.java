/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.serde2.objectinspector.primitive;

/**
 * A SettableIntObjectInspector can set an int value to an object.
 * 通过value设置和创建一个对象
 */
public interface SettableIntObjectInspector extends IntObjectInspector {

  /**
   * Set the object with the value. Return the object that has the new value.
   * 用给定的value值,重新设置o对象,返回一个对象,值是参数value的值
   * In most cases the returned value should be the same as o, but in case o is
   * unmodifiable, this will return a new object with new value.
   * 大多数情况下value都是针对参数o设置的,不会创建一个新的对象,但是当o对象是不可变对象的时候除外,会产生新的对象，比如JavaIntObjectInspector,java原生的对象作为参数
   */
  Object set(Object o, int value);

  /**
   * Create an object with the value.
   * 用给定的值,创建新的对象
   */
  Object create(int value);
}
