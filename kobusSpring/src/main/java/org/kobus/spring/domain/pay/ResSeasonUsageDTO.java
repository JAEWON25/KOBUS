package org.kobus.spring.domain.pay;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResSeasonUsageDTO {
	
	private String usageId;
	private String resId;
	private String seasonPayId;
	private Date usedDate;

}
