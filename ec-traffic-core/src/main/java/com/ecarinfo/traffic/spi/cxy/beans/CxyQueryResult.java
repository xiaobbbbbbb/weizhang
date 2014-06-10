package com.ecarinfo.traffic.spi.cxy.beans;

import java.io.Serializable;
import java.util.List;

public class CxyQueryResult implements Serializable {
	private static final long serialVersionUID = -1253839293905982554L;

	private Integer ErrorCode;
	private Boolean Success;
	private String ErrMessage;
	private Boolean HasData;
	private List<Record> Records;
	private String ResultType;
	private String LastSearchTime;
	private String Other;

	// 附加信息
	private String carNo;
	private String carFrameNo;
	private String engineNo;

	public static class Record {
		private String Time;
		private String Location;
		private String Reason;
		private String count; //罚金
		private String status;
		private String department;
		private String Degree;
		private String Code;
		private String Archive;
		private String Telephone;
		private String Excutelocation;
		private String Excutedepartment;
		private String Category;
		private String Latefine; //滞纳金
		private String Punishmentaccording;
		private String Illegalentry;
		private String Locationid;
		private String LocationName;
		private String DataSourceID;
		private String RecordType;
		private String Poundage;
		private String CanProcess;
		private String UniqueCode;
		private String SecondaryUniqueCode;
		private String DegreePoundage;
		private String Other;
		private String CanprocessMsg;
		private String CooperPoundge;

		public String getTime() {
			return Time;
		}

		public void setTime(String time) {
			Time = time;
		}

		public String getLocation() {
			return Location;
		}

		public void setLocation(String location) {
			Location = location;
		}

		public String getReason() {
			return Reason;
		}

		public void setReason(String reason) {
			Reason = reason;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getDegree() {
			return Degree;
		}

		public void setDegree(String degree) {
			Degree = degree;
		}

		public String getCode() {
			return Code;
		}

		public void setCode(String code) {
			Code = code;
		}

		public String getArchive() {
			return Archive;
		}

		public void setArchive(String archive) {
			Archive = archive;
		}

		public String getTelephone() {
			return Telephone;
		}

		public void setTelephone(String telephone) {
			Telephone = telephone;
		}

		public String getExcutelocation() {
			return Excutelocation;
		}

		public void setExcutelocation(String excutelocation) {
			Excutelocation = excutelocation;
		}

		public String getExcutedepartment() {
			return Excutedepartment;
		}

		public void setExcutedepartment(String excutedepartment) {
			Excutedepartment = excutedepartment;
		}

		public String getCategory() {
			return Category;
		}

		public void setCategory(String category) {
			Category = category;
		}

		public String getLatefine() {
			return Latefine;
		}

		public void setLatefine(String latefine) {
			Latefine = latefine;
		}

		public String getPunishmentaccording() {
			return Punishmentaccording;
		}

		public void setPunishmentaccording(String punishmentaccording) {
			Punishmentaccording = punishmentaccording;
		}

		public String getIllegalentry() {
			return Illegalentry;
		}

		public void setIllegalentry(String illegalentry) {
			Illegalentry = illegalentry;
		}

		public String getLocationid() {
			return Locationid;
		}

		public void setLocationid(String locationid) {
			Locationid = locationid;
		}

		public String getLocationName() {
			return LocationName;
		}

		public void setLocationName(String locationName) {
			LocationName = locationName;
		}

		public String getDataSourceID() {
			return DataSourceID;
		}

		public void setDataSourceID(String dataSourceID) {
			DataSourceID = dataSourceID;
		}

		public String getRecordType() {
			return RecordType;
		}

		public void setRecordType(String recordType) {
			RecordType = recordType;
		}

		public String getPoundage() {
			return Poundage;
		}

		public void setPoundage(String poundage) {
			Poundage = poundage;
		}

		public String getCanProcess() {
			return CanProcess;
		}

		public void setCanProcess(String canProcess) {
			CanProcess = canProcess;
		}

		public String getUniqueCode() {
			return UniqueCode;
		}

		public void setUniqueCode(String uniqueCode) {
			UniqueCode = uniqueCode;
		}

		public String getSecondaryUniqueCode() {
			return SecondaryUniqueCode;
		}

		public void setSecondaryUniqueCode(String secondaryUniqueCode) {
			SecondaryUniqueCode = secondaryUniqueCode;
		}

		public String getDegreePoundage() {
			return DegreePoundage;
		}

		public void setDegreePoundage(String degreePoundage) {
			DegreePoundage = degreePoundage;
		}

		public String getOther() {
			return Other;
		}

		public void setOther(String other) {
			Other = other;
		}

		public String getCanprocessMsg() {
			return CanprocessMsg;
		}

		public void setCanprocessMsg(String canprocessMsg) {
			CanprocessMsg = canprocessMsg;
		}

		public String getCooperPoundge() {
			return CooperPoundge;
		}

		public void setCooperPoundge(String cooperPoundge) {
			CooperPoundge = cooperPoundge;
		}

		@Override
		public String toString() {
			return "Record [Time=" + Time + ", Location=" + Location
					+ ", Reason=" + Reason + ", count=" + count + ", status="
					+ status + ", department=" + department + ", Degree="
					+ Degree + ", Code=" + Code + ", Archive=" + Archive
					+ ", Telephone=" + Telephone + ", Excutelocation="
					+ Excutelocation + ", Excutedepartment=" + Excutedepartment
					+ ", Category=" + Category + ", Latefine=" + Latefine
					+ ", Punishmentaccording=" + Punishmentaccording
					+ ", Illegalentry=" + Illegalentry + ", Locationid="
					+ Locationid + ", LocationName=" + LocationName
					+ ", DataSourceID=" + DataSourceID + ", RecordType="
					+ RecordType + ", Poundage=" + Poundage + ", CanProcess="
					+ CanProcess + ", UniqueCode=" + UniqueCode
					+ ", SecondaryUniqueCode=" + SecondaryUniqueCode
					+ ", DegreePoundage=" + DegreePoundage + ", Other=" + Other
					+ ", CanprocessMsg=" + CanprocessMsg + ", CooperPoundge="
					+ CooperPoundge + ", getTime()=" + getTime()
					+ ", getLocation()=" + getLocation() + ", getReason()="
					+ getReason() + ", getCount()=" + getCount()
					+ ", getStatus()=" + getStatus() + ", getDepartment()="
					+ getDepartment() + ", getDegree()=" + getDegree()
					+ ", getCode()=" + getCode() + ", getArchive()="
					+ getArchive() + ", getTelephone()=" + getTelephone()
					+ ", getExcutelocation()=" + getExcutelocation()
					+ ", getExcutedepartment()=" + getExcutedepartment()
					+ ", getCategory()=" + getCategory() + ", getLatefine()="
					+ getLatefine() + ", getPunishmentaccording()="
					+ getPunishmentaccording() + ", getIllegalentry()="
					+ getIllegalentry() + ", getLocationid()="
					+ getLocationid() + ", getLocationName()="
					+ getLocationName() + ", getDataSourceID()="
					+ getDataSourceID() + ", getRecordType()="
					+ getRecordType() + ", getPoundage()=" + getPoundage()
					+ ", getCanProcess()=" + getCanProcess()
					+ ", getUniqueCode()=" + getUniqueCode()
					+ ", getSecondaryUniqueCode()=" + getSecondaryUniqueCode()
					+ ", getDegreePoundage()=" + getDegreePoundage()
					+ ", getOther()=" + getOther() + ", getCanprocessMsg()="
					+ getCanprocessMsg() + ", getCooperPoundge()="
					+ getCooperPoundge() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}


	}

	public Integer getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(Integer errorCode) {
		ErrorCode = errorCode;
	}

	public Boolean getSuccess() {
		return Success;
	}

	public void setSuccess(Boolean success) {
		Success = success;
	}

	public String getErrMessage() {
		return ErrMessage;
	}

	public void setErrMessage(String errMessage) {
		ErrMessage = errMessage;
	}

	public Boolean getHasData() {
		return HasData;
	}

	public void setHasData(Boolean hasData) {
		HasData = hasData;
	}

	public List<Record> getRecords() {
		return Records;
	}

	public void setRecords(List<Record> records) {
		Records = records;
	}

	public String getResultType() {
		return ResultType;
	}

	public void setResultType(String resultType) {
		ResultType = resultType;
	}

	public String getLastSearchTime() {
		return LastSearchTime;
	}

	public void setLastSearchTime(String lastSearchTime) {
		LastSearchTime = lastSearchTime;
	}

	public String getOther() {
		return Other;
	}

	public void setOther(String other) {
		Other = other;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarFrameNo() {
		return carFrameNo;
	}

	public void setCarFrameNo(String carFrameNo) {
		this.carFrameNo = carFrameNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	@Override
	public String toString() {
		return "WeiZhangResult [ErrorCode=" + ErrorCode + ", Success="
				+ Success + ", ErrMessage=" + ErrMessage + ", HasData="
				+ HasData + ", Records=" + Records + ", ResultType="
				+ ResultType + ", LastSearchTime=" + LastSearchTime
				+ ", Other=" + Other + ", carNo=" + carNo + ", carFrameNo="
				+ carFrameNo + ", engineNo=" + engineNo + "]";
	}

}
