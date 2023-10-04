<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<title>application</title>
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container my-3">
	<div class="row mb-3">
		<div class="col">
			<h1 class="fs-4 border p-2 bg-light">게시글 상세정보</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col">
		<c:choose>
			<c:when test="${param.error eq 'comment' }">
				<div class="alert alert-danger">
				다른사람의 댓글은 삭제할 수 없습니다.
			</c:when>
		</c:choose>
			<p>게시글 상세정보를 확인하세요.</p>
			<c:if test="${not empty loginUserId}">
			<button class="btn btn-primary btn-sm float-end" data-bs-toggle="modal" data-bs-target="#modal-form-comments">댓글 쓰기</button>
			</c:if>
			
			<table class="table table-sm">
				<colgroup>
					<col width="15%">
					<col width="35%">
					<col width="15%">
					<col width="35%">
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="3">${post.title}</td>
					</tr>
					<tr>
						<th>번호</th>
						<td>${post.no }</td>
						<th>작성자</th>
						<td>${post.userName}</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><fmt:formatDate value="${post.createdDate }" /></td>
						<th>최종수정일</th>
						<td><fmt:formatDate value="${post.updatedDate }" /></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>10</td>
						<th>댓글수</th>
						<td>${post.commentCount }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">${post.content}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${post.userId == loginUserId }">
	<div class="row mb-3">
		<div class="col-12">
			<a href="modifyform.hta?postNo=${post.no  }" class="btn btn-warning btn-sm">수정</a>
			<a href="delete.hta?postNo=${post.no  }" class="btn btn-danger btn-sm">삭제</a>
		</div>
	</div>
	</c:if>
	<div class="row mb-3">
		<div class="col">
	<c:choose>
            <c:when test="${empty comments }">
               <div class="border p-2 mb-2">
                  <p class="mb-0">등록된 댓글이 없습니다.</p>
               </div>
            </c:when>
            <c:otherwise>
            <!-- 
            comment로 지정된 객체를 조회한다. 조회된 객체는 List<CommentListDto> 반복 수행될때마다 comment에 대입시킨다
            comment -> commentListDto [no, userId, userName, content]
             -->
               <c:forEach var="comment" items="${comments }">
                  <div class="border p-2 mb-2">
                     <p class="mb-0 small">
                        <span class="text-muted"> ${comment.userName } </span>
                        <span class="text-muted float-end"><fmt:formatDate value="${comment.createdDate }" /></span>
                     </p>
                     <p class="mb-0">
                        ${comment.content  }
                        <c:if test="${comment.userId == loginUserId }">
                        <a href="deleteComment.hta?postNo=${comment.postNo}&commentNo=${comment.no }" class="float-end"><i class="bi bi-trash-fill text-danger"></i></a>
                     	</c:if>
                     </p>
                  </div>
               	  </c:forEach>
            </c:otherwise>
         </c:choose>
		</div>
	</div>
</div>
<!-- 댓글 등록폼 -->
<div class="modal" tabindex="-1" id="modal-form-comments">
	<div class="modal-dialog">
		<form id="form-add-depts" class="p-3" method="post" action="insertComment.hta">
		<input type="hidden" name="postNo" value="${post.no }">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">댓글 등록폼</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
					<div class="row mb-2">
						<div class="col-sm-12">
							<textarea class="form-control" rows="3" name="content"></textarea>
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary btn-xs" data-bs-dismiss="modal">닫기</button>
				<button type="submit" class="btn btn-primary btn-xs">등록</button>
			</div>
		</div>
		</form>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</body>
</html>