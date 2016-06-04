package com.SMWebSer.Domain;

//�ͻ�����Ӧ���ص���Ϣ
public class CallBack {
	//��Ӧ�Ĵ���
	/*
	 * 200:��¼�ɹ�    201:ע��ɹ�   202����Ʒ�嵥          203����Ʒ����
	 * 400:��¼ʧ��    401:ע��ʧ��   402����Ʒ�嵥Ϊ��   403������Ϊ�� 
	 */
	private int status;
	//��Ϣ
	private String message;
	//json����
	private Object data;
	
	public CallBack(){}
	
	public CallBack(int status,String message,Object data){
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
