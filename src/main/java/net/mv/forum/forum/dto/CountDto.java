package net.mv.forum.forum.dto;

public class CountDto {

	private Long count;

	public CountDto() {
		super();
	}

	public CountDto(Long count) {
		super();
		this.count = count;
	}

	@Override
	public String toString() {
		return "CountDto [count=" + count + "]";
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
