package org.kobus.spring.controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.kobus.spring.domain.reservation.SeatDTO;
import org.kobus.spring.domain.schedule.ScheduleDTO;
import org.kobus.spring.service.reservation.SeatService;
import org.kobus.spring.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ReservationController {
	
	@Autowired 
	ScheduleService scheduleService;
	
	@Autowired
	SeatService seatService;
	
	@GetMapping("/kobusSeat.do")
	public String kobusSeat(
		@RequestParam(value = "deprCd", required = false) String deprId,
	    @RequestParam(value = "arvlCd", required = false) String arrId,
	    @RequestParam(value = "deprDtm", required = false) String deprdtm,
	    @RequestParam(value = "deprDtmAll", required = false) String deprDate,
	    @RequestParam(value = "orgDeprDtm", required = false) String orgDeprDtm,
	    @RequestParam(value = "deprTime", required = false) String deprTime,
	    @RequestParam(value = "arvlDtm", required = false) String arvlDtm,
	    @RequestParam(value = "arvlDtmAll", required = false) String arvlDtmAll,
	    @RequestParam(value = "busClsCd", required = false) String busClsCd,
	    @RequestParam(value = "deprNm", required = false) String deprNm,
	    @RequestParam(value = "arvlNm", required = false) String arvlNm,
	    @RequestParam(value = "pathDvs", required = false) String pathDvs,
	    @RequestParam(value = "pathStep", required = false) String pathStep,
	    @RequestParam(value = "rtrpDtl1", required = false) String rtrpDtl1,
	    @RequestParam(value = "rtrpDtl2", required = false) String rtrpDtl2,
	    
	    Model model) throws ParseException {

	    System.out.println("> SeatHandler.process() ...");
	    
//	    System.out.println("deprId : " + deprId);
//	    System.out.println("arrId : " + arrId);
//	    System.out.println("deprDate : " + deprDate);
//	    System.out.println("deprTime : " + deprTime);
//	    System.out.println("SeatHandler busClsCd : " + busClsCd);
//	    System.out.println("deprNm : " + deprNm);
//	    System.out.println("arvlNm : " + arvlNm);
//	    System.out.println("deprdtm : " + deprdtm);
//	    System.out.println("orgDeprDtm : " + orgDeprDtm);
//	    System.out.println("arvlDtm : " + arvlDtm);
//	    System.out.println("arvlDtmAll : " + arvlDtmAll);
//	    System.out.println("pathStep : " + pathStep);
	    
	    
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy. M. d. E", Locale.KOREA);

        // 3️⃣ Date 객체로 파싱
        Date date = inputFormat.parse(deprDate);

        // 4️⃣ 원하는 출력 형식 지정
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");

        // 5️⃣ 최종 변환
        String result = outputFormat.format(date);
	    
	    String deprDtm = result + " " + deprTime;
	    
	    if ("2".equals(pathStep)) {
	    	deprDtm = orgDeprDtm;

	        // 출발/도착 이름 swap
	        String tempNm = deprNm;
	        deprNm = arvlNm;
	        arvlNm = tempNm;

	        // 출발/도착 코드 swap
	        String tempId = deprId;
	        deprId = arrId;
	        arrId = tempId;

	        arvlDtm = deprdtm;
	    }
	    
	    
	    System.out.println("deprDtm : " + deprDtm);
	    System.out.println("deprNm : " + deprNm);
	    System.out.println("arvlNm : " + arvlNm);
	    System.out.println("deprId : " + deprId);
	    System.out.println("arrId : " + arrId);
	    System.out.println("arvlDtm : " + arvlDtm);
	    System.out.println("busClsCd : " + busClsCd);
	    
	    switch (busClsCd) {
		    case "0": busClsCd = "전체"; break;
		    case "7": busClsCd = "프리미엄"; break;
		    case "1": busClsCd = "우등"; break;
		    case "2": busClsCd = "일반"; break;
		    default: break;
	    }
	    
	    List<ScheduleDTO> busList = new ArrayList<ScheduleDTO>();
	    List<SeatDTO> seatList = new ArrayList<SeatDTO>();
	    String busId = "";
	    
	    try {
	    	
	    	if ("2".equals(pathStep)) {
	    		// 탑승하는 버스 스케줄 정보 가져오기
	    		
	    		String arvlFormatter = arvlDtm.replaceAll("-", "");
	    		
	    		System.out.println(arvlFormatter);
	    		
	    		busList = scheduleService.searchBusSchedule(deprId, arrId,  arvlFormatter, busClsCd);
	    		
	    		// 출발지 / 도착지 / 출발시간 / 버스등급을 기준으로 사용하는 busId 가져오기
	    		busId = seatService.getBusId(deprId, arrId,  arvlFormatter);
	    		
	    		if (arvlFormatter.length() <14) {
	    			arvlFormatter = arvlFormatter + ":00";
	    		}
	    		
	    	} else {
	    		
	    		
	    		// 탑승하는 버스 스케줄 정보 가져오기
	    		busList = scheduleService.searchBusSchedule(deprId, arrId, deprDtm, busClsCd);
	    		
	    		// 출발지 / 도착지 / 출발시간 / 버스등급을 기준으로 사용하는 busId 가져오기
	    		busId = seatService.getBusId(deprId, arrId, deprDtm);
	    		
	    		

	    		
	    		if (deprDtm.length() <14) {
	    			deprDtm = deprDtm + ":00";
	    		}
	    		
	    		
	    		deprDtm = deprDtm.substring(0, 4) + "-" + 
	    				deprDtm.substring(4, 6) + "-" + 
	    				deprDtm.substring(6, 8) + " " + deprDtm.substring(9, 14);
	    		
	    	}
	    	
    		
    		
    		// 탑승하는 버스 전체 좌석 가져오기
    		int totalSeat = seatService.getTotalSeats(busId);
    		
    		// 탑승하는 버스 좌석 정보 가져오기
    		seatList = seatService.searchSeat(busId);
	    	
	    	
	    	System.out.println("pathDvs " + pathDvs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    model.addAttribute("deprId", deprId);
	    model.addAttribute("arrId", arrId);
	    model.addAttribute("deprDtm", deprDtm);
	    model.addAttribute("deprDtmAll", deprDate );
	    model.addAttribute("deptTime", deprTime);
	    model.addAttribute("deprDate", deprDate);
	    model.addAttribute("deprTime", deprTime);
	    model.addAttribute("deprNm", deprNm);
	    model.addAttribute("arvlNm", arvlNm);
	    model.addAttribute("arvlDtm", arvlDtm);
	    model.addAttribute("arvlDtmAll", arvlDtmAll);
	    model.addAttribute("pathDvs", pathDvs);
	    model.addAttribute("pathStep", pathStep);
	    model.addAttribute("rtrpDtl1", rtrpDtl1);
	    model.addAttribute("rtrpDtl2", rtrpDtl2);
	    model.addAttribute("busList", busList);
	    model.addAttribute("seatList", seatList);
	    
	    return "kobus.reservation/kobus_seat"; 
	   
		
	}
	
	
	
	

}
