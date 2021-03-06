/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.plc4x.java.spi.generation;

import org.apache.commons.codec.binary.Hex;

import java.util.Collection;

public class StaticHelper {

    public static int ARRAY_SIZE_IN_BYTES(Object obj) {
        if (obj.getClass().isArray() && !obj.getClass().getComponentType().isPrimitive()) {
            Object[] arr = (Object[]) obj;
            int numBytes = 0;
            for (Object element : arr) {
                if(element instanceof Message) {
                    numBytes += ((Message) element).getLengthInBytes();
                } else {
                    throw new RuntimeException(
                        "Array elements for array size in bytes must implement Message interface");
                }
            }
            return numBytes;
        }
        throw new RuntimeException("Unable to calculate array size in bytes for type " + obj.getClass().getName());
    }

    public static int COUNT(Object obj) {
        if(obj == null) {
            return 0;
        }
        if (obj.getClass().isArray()) {
            if(obj.getClass().getComponentType() != null && obj.getClass().getComponentType().isPrimitive()) {
                if(obj.getClass().getComponentType() == boolean.class) {
                    boolean[] arr = (boolean[]) obj;
                    return arr.length;
                }
                if(obj.getClass().getComponentType() == byte.class) {
                    byte[] arr = (byte []) obj;
                    return arr.length;
                }
                if(obj.getClass().getComponentType() == short.class) {
                    short[] arr = (short []) obj;
                    return arr.length;
                }
                if(obj.getClass().getComponentType() == int.class) {
                    int[] arr = (int []) obj;
                    return arr.length;
                }
                if(obj.getClass().getComponentType() == long.class) {
                    long[] arr = (long []) obj;
                    return arr.length;
                }
                if(obj.getClass().getComponentType() == float.class) {
                    float[] arr = (float []) obj;
                    return arr.length;
                }
                if(obj.getClass().getComponentType() == double.class) {
                    double[] arr = (double[]) obj;
                    return arr.length;
                }
            } else {
                Object[] arr = (Object[]) obj;
                return arr.length;
            }
        } else if (obj instanceof Collection) {
            Collection col = (Collection) obj;
            return col.size();
        }
        throw new RuntimeException("Unable to count object of type " + obj.getClass().getName());
    }

    public static <T> T CAST(Object obj, Class<T> clazz) {
        try {
            return clazz.cast(obj);
        } catch (ClassCastException e) {
            throw new RuntimeException("Unable to cast object of type " + obj.getClass().getName() + " to " + clazz.getName());
        }
    }

    public static int CEIL(double value) {
        return (int) Math.ceil(value);
    }

    public static double toFloat(ReadBuffer io, boolean signed, int bitsExponent, int bitsMantissa) {
        try {
            boolean negative = (signed) && io.readBit();
            long exponent = io.readUnsignedLong(bitsExponent);
            exponent = exponent - (((long) Math.pow(2, bitsExponent) / 2) - 1);
            double mantissa = 1D;
            for(int i = 1; i <= bitsMantissa; i++) {
                if(io.readBit()) {
                    mantissa += Math.pow(2, (double) i * -1);
                }
            }
            return ((negative) ? -1 : 1) * mantissa * Math.pow(2, exponent);
        } catch(ParseException e) {
            return 0.0f;
        }
    }

    public static boolean fromFloatSign(double value) {
        return value < 0;
    }

    public static long fromFloatExponent(double value, int bitsExponent) {
        return 0;
    }

    public static long fromFloatMantissa(double value, int bitsMantissa) {
        return 0;
    }

    public static void main(String[] args) throws Exception {
        /*final byte[] bytes = Hex.decodeHex("420B9000");
        ReadBuffer io = new ReadBuffer(bytes);
        final double v = toFloat(io, true, 8, 23);
        System.out.println(v);*/

        final byte[] bytes = Hex.decodeHex("0C65");
        ReadBuffer io = new ReadBuffer(bytes);
        final double v = toFloat(io, true, 4, 11);
        System.out.println(v);
    }

}
