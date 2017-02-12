package com.gwang.spring.mybatis.demo.domain;

public class TranFlow {
    private Integer flow_seq;

    private String tran_date;

    private String acc_no;

    private String tran_state;

    private String tran_type;

    private String id_type;

    private String id_no;

	public Integer getFlow_seq() {
		return flow_seq;
	}

	public void setFlow_seq(Integer flow_seq) {
		this.flow_seq = flow_seq;
	}

	public String getTran_date() {
		return tran_date;
	}

	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public String getTran_state() {
		return tran_state;
	}

	public void setTran_state(String tran_state) {
		this.tran_state = tran_state;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

}