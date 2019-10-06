package com.db.dbhackathonapi;

public enum StatusCodeEnum {
        OK(1),
        ERROR(0),
        WARNING(2);

        private int code;

         StatusCodeEnum(int code){
                this.code=code;
        }

        public int getCode(){
                 return this.code;
        }
}
