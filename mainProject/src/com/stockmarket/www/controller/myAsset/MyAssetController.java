package com.stockmarket.www.controller.myAsset;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.AssetDistrService;
import com.stockmarket.www.service.AssetTrendService;
import com.stockmarket.www.service.basic.BasicAssetDistrService;
import com.stockmarket.www.service.basic.BasicAssetTrendService;

@WebServlet("/card/asset/myAsset")
public class MyAssetController extends HttpServlet{
	
	private AssetTrendService assetTrendService;
	private AssetDistrService assetDistrService;
	
	public MyAssetController() {
		assetTrendService = new BasicAssetTrendService();
		assetDistrService = new BasicAssetDistrService();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<RecordAsset> list = assetTrendService.getRecordAsset(3);
				
		StringBuilder builder = new StringBuilder();
	      builder.append("[");
	      // {"id":1, "title":"안녕하세요", "writerId":"newlec"}
	      // {"memberId" :
	      for(int i=0; i<list.size(); i++) {
	         String item = String.format("{\"id\":%d,\"regdate\":\"%s,\"value\":%d}"
	        		 , list.get(i).getMemberId()
	        		 , list.get(i).getRegdate()
	        		 , list.get(i).getValue());

	         builder.append(item);
	         
	         if(i < list.size()-1)
	            builder.append(",");
	      }		
	      builder.append("]");
	      String json = builder.toString();
	       
	       PrintWriter out = response.getWriter();
	       out.write(json); 
		
		
		request.getRequestDispatcher("myAsset.jsp").forward(request, response);
		
	}

}
