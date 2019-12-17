window.addEventListener("load",function(){
   
   var section = this.document.querySelector(".manageStocks");
   var updateButton = section.querySelector(".updateButton");
   var preArea = section.querySelector(".prearea");
   var backArea = section.querySelector(".backarea");
   var tbody = section.querySelector("table tbody");

	function addComma(num) {
		  var regexp = /\B(?=(\d{3})+(?!\d))/g;
		  return num.toString().replace(regexp, ',');
   }

    updateButton.onclick = function(){

    var request = new XMLHttpRequest();
    request.open("GET","../../card/managestocks/holdinglist",true);
    

    

    //서블릿의 실행이 완료되었을때 실행 
    request.onload = function(){
//        var cardFooter = section.querySelector(".card-footer");
//        console.log(cardFooter.firstChild);    
        var list = JSON.parse(request.responseText);
        
        var won = "원";
        var allIncomePercent =0;
        var allIncome = 0;
        var allSum = 0;
        tbody.innerHTML = "";
        
        for(var i=0; i<list.length; i++){
            
         var template = section.querySelector(".template");
         var cloneTr = document.importNode(template.content, true);
         var tds = cloneTr.querySelectorAll("td");
            
        	
         var incomePercent = Math.ceil((list[i].income/list[i].sum)*100);
         allIncome += list[i].income;
         allSum += list[i].sum;
         allIncomePercent += incomePercent;
         
         list[i].income = addComma(list[i].income);
         list[i].sum = addComma(list[i].sum);



         tds[0].firstElementChild.innerText = list[i].stockName;

        if(list[i].gain == "상승")
        {
           tds[1].firstElementChild.innerText = list[i].price;
           tds[1].lastElementChild.innerText = list[i].percent;
           tds[5].firstElementChild.innerText = list[i].income;
           tds[5].lastElementChild.innerText = incomePercent;
        }
        else if(list[i].gain == "하강")
        {
           tds[2].firstElementChild.innerText = list[i].price;
           tds[2].lastElementChild.innerText = list[i].percent;
           tds[6].firstElementChild.innerText = list[i].income;
           tds[6].lastElementChild.innerText = incomePercent;
        }
        else {
           tds[3].firstElementChild.innerText = list[i].price;
           tds[3].lastElementChild.innerText = list[i].percent;
           tds[7].firstElementChild.innerText = 0;
           tds[7].lastElementChild.innerText = incomePercent;
        }
        
        for(var j = 1 ; j<8; j++ ){
           if(tds[j].firstElementChild.innerText == ""){
              tds[j].style.display = "none";
            }
         }

         // if(IncomePercent>0)
         // {
         //   divs[0].firstElementChild
         // }      
//        console.log("tds"+i+"번째:"+tds[i].lastElementChild.innerText);
//        

//        for(var i =0 ; i<tds.length ;i++){
//            console.log(tds[i]);
//        }     
        tds[4].firstElementChild.innerText = list[i].quantity;
        //   var cloneTr = document.importNode(trTemplate.Content, true);
        //   var tds = cloneTr.querySelectorAll("td");
        //   tds[0].innertext = list[i].stockName;
        tbody.append(cloneTr);
        
        }
        
       allIncomePercent = allIncomePercent/list.length
       console.log(allIncomePercent+",allSum:"+allSum+",allIncome:"+allIncome); 
       var Valuation = allSum - allIncome; 

       if(allIncomePercent>0){
        preArea.firstElementChild.nextElementSibling.style.color ="red";
        preArea.firstElementChild.nextElementSibling.innerText = allIncomePercent + "%";
        preArea.lastElementChild.style.color ="red";
        preArea.lastElementChild.innerText = addComma(allIncome) + won;
        backArea.lastElementChild.style.color = "red";
        backArea.lastElementChild.innerText = addComma(Valuation) + won;
        
       }
       else if (allIncomePercent<0){
        preArea.firstElementChild.nextElementSibling.style.color ="blue";
        preArea.firstElementChild.nextElementSibling.innerText = allIncomePercent + "%";
        preArea.lastElementChild.style.color ="blue";
        preArea.lastElementChild.innerText = addComma(allIncome) + won; 
        backArea.lastElementChild.style.color = "blue";
        backArea.lastElementChild.innerText = addComma(Valuation) + won;
       }
       else{
        preArea.firstElementChild.nextElementSibling.innerText = "0%";
        preArea.lastElementChild.innerText = 0 + won;
        backArea.lastElementChild.innerText = 0 + won;
       }
       
       backArea.firstElementChild.nextElementSibling.innerText = addComma(allSum) + won;
       
    };
    request.send();
   }

});