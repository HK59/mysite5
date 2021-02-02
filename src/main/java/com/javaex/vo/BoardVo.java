package com.javaex.vo;

public class BoardVo {

//field
	private int no;
	private String title;
	private String content;
	private int hit;
	private String regdate;
	private int userno;
	private String name;

//constructor
	public BoardVo() {
		super();
	}

	public BoardVo(int no, String title, String content, int hit, String regdate, int userno, String name) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regdate = regdate;
		this.userno = userno;
		this.name = name;
	}

	// getter-setter

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//tostring

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regdate="
				+ regdate + ", userno=" + userno + ", name=" + name + "]";
	}

}