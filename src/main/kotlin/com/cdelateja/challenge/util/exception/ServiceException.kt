package com.cdelateja.challenge.util.exception

class ServiceException : Exception {

    var status: Int? = null

    constructor() {}

    constructor(message: String?): super(message)

    constructor(status: Int?, message: String?): super(message) {
        this.status = status
    }

    override fun toString(): String {
        return "Exception : {" +
                "status=" + status +
                " " +
                "message=" + message +
                '}'
    }
}
