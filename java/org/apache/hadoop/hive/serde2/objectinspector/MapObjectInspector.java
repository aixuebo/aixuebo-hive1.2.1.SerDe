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
package org.apache.hadoop.hive.serde2.objectinspector;

import java.util.Map;

/**
 * MapObjectInspector.
 *
 */
public interface MapObjectInspector extends ObjectInspector {

  // ** Methods that does not need a data object **
  // Map Type
  //key类型
  ObjectInspector getMapKeyObjectInspector();
  //value类型
  ObjectInspector getMapValueObjectInspector();

  // ** Methods that need a data object **
  // In this function, key has to be of the same structure as the Map expects.
  // Most cases key will be primitive type, so it's OK.
  // In rare cases that key is not primitive, the user is responsible for
  // defining
  // the hashCode() and equals() methods of the key class.

    /**
     * @param data Map<?, ?> map = (Map<?, ?>) data; 即data就是全局的对象,在该类中要强制转换成Map即可
     * @param key key的值
     * @return 获取key对应的value
     */
  Object getMapValueElement(Object data, Object key);

  /**
   * returns null for data = null.
   * 
   * Note: This method should not return a Map object that is reused by the same
   * MapObjectInspector, because it's possible that the same MapObjectInspector
   * will be used in multiple places in the code.
   * 
   * However it's OK if the Map object is part of the Object data.
   * @param data Map<?, ?> map = (Map<?, ?>) data; 即data就是全局的对象,在该类中要强制转换成Map即可
   * 返回map的内容
   */
  Map<?, ?> getMap(Object data);

  /**
   * returns -1 for NULL map.
   * @param data Map<?, ?> map = (Map<?, ?>) data; 即data就是全局的对象,在该类中要强制转换成Map即可
   * 返回参数map的大小
   */
  int getMapSize(Object data);
}
