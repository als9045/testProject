<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>��ǰ�����ȸ</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<!-- jQuery UI CDN(Content Delivery Network) ȣ��Ʈ ��� -->
<!-- CDN(Content Delivery Network) ȣ��Ʈ ��� -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	
		function fncGetProductList(currentPage) {
			$("#currentPage").val(currentPage)
		   	//document.detailForm.submit();
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		}
		//===========================================//
		//==> �߰��Ⱥκ� : "�˻�" ,  userId link  Event ���� �� ó��
		 $(function() {
			 
			 //�ʱ� �ε� �� ��ũ�ѹ��� ���̿� ������ ���̸� �� ��ũ�� �Ʒ� ���� ������ �ε�
		 /* 	 if($(window).height() == $(document).height()){
				
				 loadMoreDate()
				 
			 }  */
			 //���� �������� ���ѽ�ũ�� Ȱ��ȭ ����
			 let currentPage = 1;
			 let infiniteScrollEnabled = true;
			 
			 //���ѽ�ũ��(��ũ�ѻ��� event??)
			 window.addEventListener("scroll", function() {			 
			 
			//������ ��ü����
			 let scrollHeight = document.documentElement.scrollHeight;
			 
			//window.innerHeight = ���纸�̴� ������ â ����
			//window.scrollY ���� ��ũ�� �� ��ġ
			 let scrollPosition = window.innerHeight + window.scrollY;
			console.log(scrollPosition)
			console.log(scrollHeight)
			
			///üũ�ذ��鼭 window�� documentȮ��
			console.log("cex"+infiniteScrollEnabled)
			 
			//scrollHeight = ������ü����  scrollPosition = ��ũ�ѳ���
			//scrollHeight = scrollPosition == ���ϴ�
/* 			 if (infiniteScrollEnabled && (scrollHeight - scrollPosition) / scrollHeight === 0) {
				    infiniteScrollEnabled = true; // �ߺ� ��û�� ���� ���� Ȱ��ȭ ���¸� ��Ȱ��ȭ�� ����
				    loadMoreData();
				  }
				}); */
				
		     ///scrollHeight - scrollPosition < 10�� �׽�Ʈ�� �����̹Ƿ� �ּ����� ȣ���� �ϵ��� �����Ұ�
		     //scrollHeight - scrollPosition < 1 �����ص� �Ϸ�
			 if (infiniteScrollEnabled && scrollHeight - scrollPosition < 1) {
				    infiniteScrollEnabled = false; // �ߺ� ��û�� ���� ���� Ȱ��ȭ ���¸� ��Ȱ��ȭ�� ����
				    loadMoreData();
				  }
				});
			
			
			 function loadMoreData() {
				    let searchConditionValue = $('select[name="searchCondition"]').val();
				    let searchKeywordValue = $('input[name="searchKeyword"]').val();
				    let currentPageValue = parseInt($('input[name="currentPage"]').val());
				    currentPageValue++;

				    $('input[name="currentPage"]').val(currentPageValue);

				    $.ajax({
				        url: "/product/json/listProduct",
				        data: JSON.stringify({
				            currentPage: currentPageValue,
				            searchKeyword: searchKeywordValue,
				            searchCondition: searchConditionValue
				        }),
				        method: "POST",
				        contentType: "application/json",
				        dataType: "json",
				        success: function (data, status) {
				            let prodList = data.list;
				            let resultPage = data.resultPage;

				            prodList.forEach(function (product) {
				                // �������� �����Ǵ� �࿡ .product-row Ŭ������ �߰��Ͽ� ��Ÿ�� ����
				                let row = "<tr class='ct_list_pop'>"
				                    + "<td align=center width=100>" + product.prodName + "</td>"
				                    + "<td></td>"
				                    + "<td align=center width=100>" + product.prodNo + "</td>"
				                    + "<a href='/product/";

				                if ("${param.menu}" === 'manage') {
				                    row += "updateProduct?prodNo=" + product.prodNo + "&menu=manage";
				                } else if ("${param.menu}" === 'search') {
				                    row += "getProduct?prodNo=" + product.prodNo + "&menu=search";
				                }

				                row += "'/>"
				                    + "<td></td>"
				                    + "<td align=center>" + product.price + "</td>"
				                    + "<td></td>"
				                    + "<td align=center width=100>" + product.regDate + "</td>"
				                    + "<td></td>"
				                    + "</tr>";

				                $('.cex').append(row);
				            });

				            infiniteScrollEnabled = true;
				        },
				    });
				}

			 
			 
			 
			 
			 
			//==> �˻� Event ����ó���κ�
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 1 �� 3 ��� ���� : $("tagName.className:filter�Լ�") �����. 
			
			$( "td.ct_btn01:contains('�˻�')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('�˻�')" ).html() );
				fncGetProductList(1);
			});
			
			 $( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					
					
					
					var name =  $(this).closest(".ct_list_pop");
					var prodNo = name.find("td:nth-child(5)").text().trim();
					//alert("�� Ȯ��: " + prodNo);
					
					var menu = "${menu}";
					//alert("�� Ȯ��: " + menu);
					if(menu ==='manage'){
						self.location = "/product/updateProduct?prodNo=" +prodNo;
					}else{
					
			          self.location = "/product/getProduct?prodNo=" +prodNo;
			      
					}
				});
			//==> userId LINK Event ����ó��
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 3 �� 1 ��� ���� : $(".className tagName:filter�Լ�") �����.
			
			/* $( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					
				
			
				var name =  $(this).closest(".ct_list_pop");
				var prodNo = name.find("td:nth-child(5)").text().trim();
				
				
				var menu = "${menu}";
				
				if(menu ==='manage'){
					self.location = "/product/updateProduct?prodNo=" +prodNo;
				}else if (menu ==='search'){
					
					var prodName = $(this).text().trim();
					
					$.ajax({
								url : "/product/json/getProduct/"+prodNo ,
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function(JSONData , status) {

									//Debug...
									
									//Debug...
									
									var displayValue = "<h3>"
																+"��ǰ��: "+JSONData.prodName+"<br/>"
																+"������ : "+JSONData.prodDetail+"<br/>"
																+"�������� : "+JSONData.regDate+"<br/>"
																+"�� �� : "+JSONData.price+"<br/>"
																//+"�� �� : "+JSONData.fileName+"<br/>"
																//+"�� �� : "+load('JSONData.fileName')+"<br/>"
																+"</h3>";
									//Debug...									
									
									$("h3").remove();
									$( "#"+prodName+"" ).html(displayValue);
								}
						});
						////////////////////////////////////////////////////////////////////////////////////////////
				}	
			}); */
		 
			//==> UI ���� �߰��κ�  :  userId LINK Event End User ���� ���ϼ� �ֵ��� 
			
			$("h7").css("color" , "red");
			
			
			//==> �Ʒ��� ���� ������ ������ ??
			//==> �Ʒ��� �ּ��� �ϳ��� Ǯ�� ���� �����ϼ���.					
			//$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
			console.log ( $(".ct_list_pop:nth-child(1)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(2)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(3)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(4)" ).html() ); //==> ok
			//console.log ( $(".ct_list_pop:nth-child(5)" ).html() ); 
			//console.log ( $(".ct_list_pop:nth-child(6)" ).html() ); //==> ok
			//console.log ( $(".ct_list_pop:nth-child(7)" ).html() ); 
		});	

</script>



</head>
<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">
<input type="hidden" name="currentPage" value="1" />
<input type="hidden" name="proNo" value="${product.prodNo}" />
<form name="detailForm" action="/product/listProduct?menu=${menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					
					<td width="93%" class="ct_ttl01">
					${menu eq 'manage' ? "��ǰ����" : "��ǰ�����ȸ"}	
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		
				<option value="0" ${ ! empty sera.search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
				<option value="1" ${ ! empty sera.search.searchCondition && search.searchCondition==0 ? "selected" : "" }>��ǰ��</option>
				
		</select>
			<input type="text" name="searchKeyword"  value="${! empty search.searchKeyword ? search.searchKeyword : '' }"
			class="ct_input_g" style="width:200px; height:19px" >
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						�˻�
						<!-- <a href="javascript:fncGetproductList(1);">�˻�</a> -->
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<!-- id�� �׽�Ʈ �� �����Ұ�. �����ϴ� ������ ������ -->
<table class="cex" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11"  >
		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">�̹���</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="250">����</td>	
		<td class="ct_line02" ></td>
		<td class="ct_list_b" width="700">�����</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
			<td></td>
			<td align="center"width="100">${product.prodName}</td>
			<td></td>
			<td align="center"width="100">${product.prodNo}</td>
			<td></td>
			<td align="center">${product.price}
			<td></td>
			<td align="center"width="100">
			<img src="/images/uploadFiles/${product.fileName}" width="100px" height="100px"/>
			</td>
			<td></td>		
		</tr>
		<tr>
		<td id="${product.prodName}"colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>

		
	


<%-- <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>

    		<jsp:include page="../common/pageNavigator.jsp">
    				<jsp:param name="type" value="Product"/>
    		</jsp:include>	
    	</td>
	</tr>
</table> --%>
<!--  ������ Navigator �� -->

</form>
</div>

</body>
</html>
