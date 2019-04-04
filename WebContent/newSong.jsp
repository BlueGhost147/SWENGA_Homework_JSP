<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/bootstrapMeta.jsp" />
<title>Songs</title>
<jsp:include page="includes/bootstrapCss.jsp" />
<link
	href="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
	rel="stylesheet">
</head>
<body>
	<div class="container" role="main">

		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form class="form-horizontal" method="post" action="saveNewSong">
					<fieldset>
						<legend>New Song</legend>

						<div class="form-group">
							<label for="inputId" class="col-md-2 control-label">Id</label>
							<div class="col-md-10">
								<input class="form-control" id="inputId" type="number" name="id"
									value="">
							</div>
						</div>
						<div class="form-group">
							<label for="inputSongName" class="col-md-2 control-label">Song
								name</label>
							<div class="col-md-10">
								<input class="form-control" id="inputSongName" type="text"
									name="songName" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="inputArtist" class="col-md-2 control-label">Artist</label>
							<div class="col-md-10">
								<input class="form-control" id="inputArtist" type="text"
									name="artist" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="inputAlbum" class="col-md-2 control-label">Album</label>
							<div class="col-md-10">
								<input class="form-control" id="inputAlbum" type="text"
									name="album" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="releaseDate" class="col-md-2 control-label">Release
								date</label>
							<div class="col-md-10">
								<input class="form_datetime" id="inputReleaseDate"
									placeholder="Date" type="text" readonly name="releaseDate"
									value="">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-10 col-md-offset-2">
								<button type="submit" class="btn btn-primary">Submit</button>
								<a href="listSongs" class="btn btn-default">Cancel</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

	</div>
	<!--  End of container -->


	<!-- JS for Bootstrap -->
	<%@include file="includes/bootstrapJs.jsp"%>
	<!-- JS for Bootstrap -->


	<!-- JS for Datetime picker -->

	<script type="text/javascript"
		src="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>

	<script>
		$(function() {

			$(".form_datetime").datetimepicker({
				format : "dd.mm.yyyy",
				autoclose : true,
				todayBtn : true,
				pickerPosition : "bottom-left",
				minView : 2
			});

		});
	</script>

</body>
</html>