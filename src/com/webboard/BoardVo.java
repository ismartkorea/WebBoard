package com.webboard;

import java.sql.Timestamp;

public class BoardVo {
	
	private int no;
	private String title;
	private String content;
	private String regName;
	private Timestamp regDate;
	private String updName;
	private Timestamp updDate;
	//
	public String getUpdName() {
		return updName;
	}
	public void setUpdName(String updName) {
		this.updName = updName;
	}
	
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
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + no;
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((regName == null) ? 0 : regName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((updDate == null) ? 0 : updDate.hashCode());
		result = prime * result + ((updName == null) ? 0 : updName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVo other = (BoardVo) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (no != other.no)
			return false;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		if (regName == null) {
			if (other.regName != null)
				return false;
		} else if (!regName.equals(other.regName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (updDate == null) {
			if (other.updDate != null)
				return false;
		} else if (!updDate.equals(other.updDate))
			return false;
		if (updName == null) {
			if (other.updName != null)
				return false;
		} else if (!updName.equals(other.updName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content="
				+ content + ", regName=" + regName + ", regDate=" + regDate
				+ ", updName=" + updName + ", updDate=" + updDate + "]";
	}
	
	

}
