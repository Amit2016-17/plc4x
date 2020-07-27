/*
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

#ifndef PLC4C_S7_READ_WRITE_DEVICE_GROUP_H_
#define PLC4C_S7_READ_WRITE_DEVICE_GROUP_H_

#include <stdbool.h>
#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

enum plc4c_s7_read_write_device_group {
  plc4c_s7_read_write_device_group_PG_OR_PC = 0x01,
  plc4c_s7_read_write_device_group_OS = 0x02,
  plc4c_s7_read_write_device_group_OTHERS = 0x03
};
typedef enum plc4c_s7_read_write_device_group plc4c_s7_read_write_device_group;

// Get an empty NULL-struct
plc4c_s7_read_write_device_group plc4c_s7_read_write_device_group_null();


#ifdef __cplusplus
}
#endif

#endif  // PLC4C_S7_READ_WRITE_DEVICE_GROUP_H_