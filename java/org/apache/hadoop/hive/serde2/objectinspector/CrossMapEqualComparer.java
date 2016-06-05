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
/*
 * The equality is implemented fully, the greater-than/less-than
 * values do not implement a transitive relation.
 * 该排序map是最耗时的,与FullMapEqualComparer排序差不多,只不过没有预先对key进行排序,而是通过for  for两层循环进行key排序的
 */
public class CrossMapEqualComparer implements MapEqualComparer {
  @Override
  public int compare(Object o1, MapObjectInspector moi1, Object o2, MapObjectInspector moi2) {
    //获取map的size,并且比较大小
    int mapsize1 = moi1.getMapSize(o1);
    int mapsize2 = moi2.getMapSize(o2);
    if (mapsize1 != mapsize2) {
      return mapsize1 - mapsize2;
    }
    //获取key的类型
    ObjectInspector mkoi1 = moi1.getMapKeyObjectInspector();
    ObjectInspector mkoi2 = moi2.getMapKeyObjectInspector();

    //获取value的类型
    ObjectInspector mvoi1 = moi1.getMapValueObjectInspector();
    ObjectInspector mvoi2 = moi2.getMapValueObjectInspector();

    //获取map的key-value内容
    Map<?, ?> map1 = moi1.getMap(o1);
    Map<?, ?> map2 = moi2.getMap(o2);

    for (Object mk1 : map1.keySet()) {//循环map1的所有key
      boolean notFound = true;
      for (Object mk2 : map2.keySet()) {
        int rc = ObjectInspectorUtils.compare(mk1, mkoi1, mk2, mkoi2, this);//比较key是否相同
        if (rc != 0) {//如果key不相同,则继续寻找下一个key
              continue;
          }
        notFound = false;//代码走到这里,说明找到key相同的了

        //对于key相同的,比较value
        //获取对应的vlaue值
        Object mv1 = map1.get(mk1);
        Object mv2 = map2.get(mk2);
        rc = ObjectInspectorUtils.compare(mv1, mvoi1, mv2, mvoi2, this);
        if (rc != 0) {
          return rc;
        } else {
          break;
        }
      }
      if (notFound) {//说明没有发现key相同的,则直接返回1,因为已经比较好大小了
        return 1;
      }
    }
    return 0;
  }

}
