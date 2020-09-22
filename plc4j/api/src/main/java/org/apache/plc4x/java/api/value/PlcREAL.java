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

package org.apache.plc4x.java.api.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.plc4x.java.api.exceptions.PlcInvalidFieldException;

import org.apache.plc4x.java.api.value.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
public class PlcREAL extends PlcIECValue<Float> {

    static Float minValue = -Float.MAX_VALUE;
    static Float maxValue = Float.MAX_VALUE;

    public PlcREAL(Boolean value) {
        super();
        this.value = value ? (Float) 1.0f : (Float) 0.0f;
        this.isNullable = false;
    }

    public PlcREAL(Byte value) {
        super();
        this.value = (Float) value.floatValue();
        this.isNullable = false;
    }

    public PlcREAL(Short value) {
        super();
        this.value = (Float) value.floatValue();
        this.isNullable = false;
    }

    public PlcREAL(Integer value) {
        super();
        this.value = (Float) value.floatValue();
        this.isNullable = false;
    }

    public PlcREAL(Float value) {
        super();
        this.value = value;
        this.isNullable = false;
    }

    public PlcREAL(Double value) {
        super();
        if ((value >= minValue) && (value <= maxValue)) {
            this.value = (Float) value.floatValue();
            this.isNullable = false;
        } else {
            throw new PlcInvalidFieldException("Value of type " + value +
              " is out of range " + minValue + " - " + maxValue + " for a " +
              this.getClass().getSimpleName() + " Value");
        }
    }

    public PlcREAL(BigInteger value) {
        super();
        BigDecimal val = new BigDecimal(value);
        if ((val.compareTo(BigDecimal.valueOf(minValue)) >= 0) && (val.compareTo(BigDecimal.valueOf(maxValue)) <= 0)) {
            this.value = (Float) val.floatValue();
            this.isNullable = true;
        } else {
          throw new PlcInvalidFieldException("Value of type " + value +
            " is out of range " + minValue + " - " + maxValue + " for a " +
            this.getClass().getSimpleName() + " Value");
        }
    }

    public PlcREAL(BigDecimal value) {
        super();
        if ((value.compareTo(BigDecimal.valueOf(minValue)) >= 0) && (value.compareTo(BigDecimal.valueOf(maxValue)) <= 0) && (value.scale() <= 0)) {
            this.value = (Float) value.floatValue();
            this.isNullable = true;
        } else {
          throw new PlcInvalidFieldException("Value of type " + value +
            " is out of range " + minValue + " - " + maxValue + " for a " +
            this.getClass().getSimpleName() + " Value");
        }
    }

    public PlcREAL(String value) {
        super();
        try {
            Float val = Float.parseFloat(value);
            this.value = val;
            this.isNullable = false;
        }
        catch(Exception e) {
          throw new PlcInvalidFieldException("Value of type " + value +
            " is out of range " + minValue + " - " + maxValue + " for a " +
            this.getClass().getSimpleName() + " Value");
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PlcREAL(@JsonProperty("value") float value) {
        super();
        this.value = new Float(value);
        this.isNullable = false;
    }

    @Override
    @JsonIgnore
    public boolean isFloat() {
        return true;
    }

    @Override
    @JsonIgnore
    public float getFloat() {
        return value;
    }

    public float getREAL() {
        return value;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return Float.toString(value);
    }

    public byte[] getBytes() {
        int intBits =  Float.floatToIntBits(value);
	      return new byte[] { (byte) (intBits >> 24), (byte) (intBits >> 16), (byte) (intBits >> 8), (byte) (intBits) };
    }

}