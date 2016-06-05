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

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.ByteStream;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyInteger;
import org.apache.hadoop.hive.serde2.lazy.LazyLong;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorConverters.Converter;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;

/**
 * PrimitiveObjectInspectorConverter.
 * 原始类型转换
 * 只要看明白以下类就可以以此类推了
 * 1.class BooleanConverter implements Converter  将输入类型的参数转换成boolean类型的输出
 * 2.class TextConverter implements Converter 一个帮助类,去将任意类型的对象转换成Text类型的输出
 */
public class PrimitiveObjectInspectorConverter {

  /**
   * A converter for the byte type.
   * 将输入类型的参数转换成boolean类型的输出
   */
  public static class BooleanConverter implements Converter {
    PrimitiveObjectInspector inputOI;//输入对象类型
    SettableBooleanObjectInspector outputOI;//可以设置值,并且知道输出对象类型
    Object r;//转换后的值实例对象

    public BooleanConverter(PrimitiveObjectInspector inputOI,
        SettableBooleanObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(false);//根据输出对象类型,初始化输出对象实例
    }

      //转换,将输入对象的实例,该对象的类型详参见inputOI
    @Override
    public Object convert(Object input) {
      if (input == null) {//如果输入不是null,则要进行转换
        return null;
      }
      try {
          /**
           * 对输出类型的实例r,进行重新设置,重新设置的值是PrimitiveObjectInspectorUtils.getBoolean(input,inputOI)确定的
           *
           * PrimitiveObjectInspectorUtils.getBoolean(input,inputOI)该方法就是将输入对象和输入参数转换成对应的boolean类型对象
           */
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getBoolean(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * A converter for the byte type.
   */
  public static class ByteConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableByteObjectInspector outputOI;
    Object r;

    public ByteConverter(PrimitiveObjectInspector inputOI,
        SettableByteObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create((byte) 0);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      try {
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getByte(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * A converter for the short type.
   */
  public static class ShortConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableShortObjectInspector outputOI;
    Object r;

    public ShortConverter(PrimitiveObjectInspector inputOI,
        SettableShortObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create((short) 0);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      try {
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getShort(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * A converter for the int type.
   */
  public static class IntConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableIntObjectInspector outputOI;
    Object r;

    public IntConverter(PrimitiveObjectInspector inputOI,
        SettableIntObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(0);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      try {
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getInt(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * A converter for the long type.
   */
  public static class LongConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableLongObjectInspector outputOI;
    Object r;

    public LongConverter(PrimitiveObjectInspector inputOI,
        SettableLongObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(0);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      try {
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getLong(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * A converter for the float type.
   */
  public static class FloatConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableFloatObjectInspector outputOI;
    Object r;

    public FloatConverter(PrimitiveObjectInspector inputOI,
        SettableFloatObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(0);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      try {
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getFloat(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  /**
   * A converter for the double type.
   */
  public static class DoubleConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableDoubleObjectInspector outputOI;
    Object r;

    public DoubleConverter(PrimitiveObjectInspector inputOI,
        SettableDoubleObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(0);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      try {
        return outputOI.set(r, PrimitiveObjectInspectorUtils.getDouble(input,
            inputOI));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  public static class DateConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableDateObjectInspector outputOI;
    Object r;

    public DateConverter(PrimitiveObjectInspector inputOI,
        SettableDateObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(new Date(0));
    }

    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      return outputOI.set(r, PrimitiveObjectInspectorUtils.getDate(input,
          inputOI));
    }
  }

  public static class TimestampConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableTimestampObjectInspector outputOI;
    boolean intToTimestampInSeconds = false;
    Object r;

    public TimestampConverter(PrimitiveObjectInspector inputOI,
        SettableTimestampObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(new Timestamp(0));
    }

    public void setIntToTimestampInSeconds(boolean intToTimestampInSeconds) {
      this.intToTimestampInSeconds = intToTimestampInSeconds;
    }
 
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      return outputOI.set(r, PrimitiveObjectInspectorUtils.getTimestamp(input,
          inputOI, intToTimestampInSeconds));
    }
  }

  public static class HiveIntervalYearMonthConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableHiveIntervalYearMonthObjectInspector outputOI;
    Object r;

    public HiveIntervalYearMonthConverter(PrimitiveObjectInspector inputOI,
        SettableHiveIntervalYearMonthObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(new HiveIntervalYearMonth());
    }

    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      return outputOI.set(r, PrimitiveObjectInspectorUtils.getHiveIntervalYearMonth(input, inputOI));
    }
  }

  public static class HiveIntervalDayTimeConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableHiveIntervalDayTimeObjectInspector outputOI;
    Object r;

    public HiveIntervalDayTimeConverter(PrimitiveObjectInspector inputOI,
        SettableHiveIntervalDayTimeObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(new HiveIntervalDayTime());
    }

    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      return outputOI.set(r, PrimitiveObjectInspectorUtils.getHiveIntervalDayTime(input, inputOI));
    }
  }

  public static class HiveDecimalConverter implements Converter {

    PrimitiveObjectInspector inputOI;
    SettableHiveDecimalObjectInspector outputOI;
    Object r;

    public HiveDecimalConverter(PrimitiveObjectInspector inputOI,
        SettableHiveDecimalObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      this.r = outputOI.create(HiveDecimal.ZERO);
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }

      return outputOI.set(r, PrimitiveObjectInspectorUtils.getHiveDecimal(input, inputOI));
    }
  }

  public static class BinaryConverter implements Converter{

    PrimitiveObjectInspector inputOI;
    SettableBinaryObjectInspector outputOI;
    Object r;

    public BinaryConverter(PrimitiveObjectInspector inputOI,
        SettableBinaryObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      r = outputOI.create(new byte[]{});
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }

      return outputOI.set(r, PrimitiveObjectInspectorUtils.getBinary(input,
          inputOI));
    }

  }

  /**
   * A helper class to convert any primitive to Text.
   *  一个帮助类,去将任意类型的对象转换成Text类型的输出
   */
  public static class TextConverter implements Converter {
    private final PrimitiveObjectInspector inputOI;//输出任意类型参数
    private final Text t = new Text();//输出值Text
    private final ByteStream.Output out = new ByteStream.Output();

    //定义boolean类型的字节数组
    private static byte[] trueBytes = {'T', 'R', 'U', 'E'};
    private static byte[] falseBytes = {'F', 'A', 'L', 'S', 'E'};

    //设置输出类型
    public TextConverter(PrimitiveObjectInspector inputOI) {
      // The output ObjectInspector is writableStringObjectInspector.
      this.inputOI = inputOI;
    }

    //将输入类型对应的输入值转换成Text对象
    public Text convert(Object input) {
      if (input == null) {
        return null;
      }

      switch (inputOI.getPrimitiveCategory()) {
      case VOID:
        return null;
      case BOOLEAN:
        t.set(((BooleanObjectInspector) inputOI).get(input) ? trueBytes
            : falseBytes);
        return t;
      case BYTE:
        out.reset();
        LazyInteger.writeUTF8NoException(out, ((ByteObjectInspector) inputOI).get(input));//将输入字节数组,转换到输出中
        t.set(out.getData(), 0, out.getLength());//将输出设置成Text对象
        return t;
      case SHORT:
        out.reset();
        LazyInteger.writeUTF8NoException(out, ((ShortObjectInspector) inputOI).get(input));
        t.set(out.getData(), 0, out.getLength());
        return t;
      case INT:
        out.reset();
        LazyInteger.writeUTF8NoException(out, ((IntObjectInspector) inputOI).get(input));
        t.set(out.getData(), 0, out.getLength());
        return t;
      case LONG:
        out.reset();
        LazyLong.writeUTF8NoException(out, ((LongObjectInspector) inputOI).get(input));
        t.set(out.getData(), 0, out.getLength());
        return t;
      case FLOAT:
        t.set(String.valueOf(((FloatObjectInspector) inputOI).get(input)));
        return t;
      case DOUBLE:
        t.set(String.valueOf(((DoubleObjectInspector) inputOI).get(input)));
        return t;
      case STRING:
        if (inputOI.preferWritable()) {
          t.set(((StringObjectInspector) inputOI).getPrimitiveWritableObject(input));
        } else {
          t.set(((StringObjectInspector) inputOI).getPrimitiveJavaObject(input));
        }
        return t;
      case CHAR:
        // when converting from char, the value should be stripped of any trailing spaces.
        if (inputOI.preferWritable()) {
          // char text value is already stripped of trailing space
          t.set(((HiveCharObjectInspector) inputOI).getPrimitiveWritableObject(input)
              .getStrippedValue());
        } else {
          t.set(((HiveCharObjectInspector) inputOI).getPrimitiveJavaObject(input).getStrippedValue());
        }
        return t;
      case VARCHAR:
        if (inputOI.preferWritable()) {
          t.set(((HiveVarcharObjectInspector) inputOI).getPrimitiveWritableObject(input)
              .toString());
        } else {
          t.set(((HiveVarcharObjectInspector) inputOI).getPrimitiveJavaObject(input).toString());
        }
        return t;
      case DATE:
        t.set(((DateObjectInspector) inputOI).getPrimitiveWritableObject(input).toString());
        return t;
      case TIMESTAMP:
        t.set(((TimestampObjectInspector) inputOI)
            .getPrimitiveWritableObject(input).toString());
        return t;
      case INTERVAL_YEAR_MONTH:
        t.set(((HiveIntervalYearMonthObjectInspector) inputOI)
            .getPrimitiveWritableObject(input).toString());
        return t;
      case INTERVAL_DAY_TIME:
        t.set(((HiveIntervalDayTimeObjectInspector) inputOI)
            .getPrimitiveWritableObject(input).toString());
        return t;
      case BINARY:
        BinaryObjectInspector binaryOI = (BinaryObjectInspector) inputOI;
        if (binaryOI.preferWritable()) {
          BytesWritable bytes = binaryOI.getPrimitiveWritableObject(input);
          t.set(bytes.getBytes(), 0, bytes.getLength());
        } else {
          t.set(binaryOI.getPrimitiveJavaObject(input));
        }
        return t;
      case DECIMAL:
        t.set(((HiveDecimalObjectInspector) inputOI).getPrimitiveWritableObject(input).toString());
        return t;
      default:
        throw new RuntimeException("Hive 2 Internal error: type = " + inputOI.getTypeName());
      }
    }
  }

  /**
   * A helper class to convert any primitive to String.
   */
  public static class StringConverter implements Converter {
    PrimitiveObjectInspector inputOI;

    public StringConverter(PrimitiveObjectInspector inputOI) {
      // The output ObjectInspector is writableStringObjectInspector.
      this.inputOI = inputOI;
    }

    @Override
    public Object convert(Object input) {
      return PrimitiveObjectInspectorUtils.getString(input, inputOI);
    }
  }


  public static class HiveVarcharConverter implements Converter {

    PrimitiveObjectInspector inputOI;
    SettableHiveVarcharObjectInspector outputOI;
    HiveVarcharWritable hc;

    public HiveVarcharConverter(PrimitiveObjectInspector inputOI,
        SettableHiveVarcharObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;

      // unfortunately we seem to get instances of varchar object inspectors without params
      // when an old-style UDF has an evaluate() method with varchar arguments.
      // If we disallow varchar in old-style UDFs and only allow GenericUDFs to be defined
      // with varchar arguments, then we might be able to enforce this properly.
      //if (typeParams == null) {
      //  throw new RuntimeException("varchar type used without type params");
      //}
      hc = new HiveVarcharWritable();
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      switch (inputOI.getPrimitiveCategory()) {
        case BOOLEAN:
          return outputOI.set(hc,
              ((BooleanObjectInspector) inputOI).get(input) ?
                  new HiveVarchar("TRUE", -1) : new HiveVarchar("FALSE", -1));
        default:
          return outputOI.set(hc, PrimitiveObjectInspectorUtils.getHiveVarchar(input, inputOI));
      }
    }

  }

  public static class HiveCharConverter implements Converter {
    PrimitiveObjectInspector inputOI;
    SettableHiveCharObjectInspector outputOI;
    HiveCharWritable hc;

    public HiveCharConverter(PrimitiveObjectInspector inputOI,
        SettableHiveCharObjectInspector outputOI) {
      this.inputOI = inputOI;
      this.outputOI = outputOI;
      hc = new HiveCharWritable();
    }

    @Override
    public Object convert(Object input) {
      if (input == null) {
        return null;
      }
      switch (inputOI.getPrimitiveCategory()) {
      case BOOLEAN:
        return outputOI.set(hc,
            ((BooleanObjectInspector) inputOI).get(input) ?
                new HiveChar("TRUE", -1) : new HiveChar("FALSE", -1));
      default:
        return outputOI.set(hc, PrimitiveObjectInspectorUtils.getHiveChar(input, inputOI));
      }
    }
  }
}
