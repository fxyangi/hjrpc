package com.hjrpc.base;

import com.hjrpc.constants.Constants;

public class BaseApiService {
	
	public ResponseBase setResult(String code, String msg, Object data) {
		return new ResponseBase(code, msg, data);
	}

	public ResponseBase setSuccessResult(Object data) {
		return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_CODE_MSG, data);
	}

	public ResponseBase setSuccessResult() {
		return new ResponseBase(Constants.SUCCESS_CODE, Constants.SUCCESS_CODE_MSG, null);
	}

	public ResponseBase setErrorResult(String errorMsg) {
		return new ResponseBase(Constants.ERROR_CODE, Constants.ERROR_CODE_MSG, null);
	}
}
