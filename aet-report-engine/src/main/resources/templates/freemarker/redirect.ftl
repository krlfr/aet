<#--

    Cognifide AET :: Report Engine

    Copyright (C) 2013 Cognifide Limited

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->
<!DOCTYPE html>
<html>
	<head>
		<title>Redirect to AET Test Results</title>
		<meta content="text/html; charset=utf-8" http-equiv="content-type">
		<meta http-equiv="refresh" content="0; url=${fullReportUrl}" />
		<script>
			window.location.href = "${fullReportUrl}"
		</script>
	</head>
	<body>
		<p>If you are not redirected automatically, follow the <a href="${fullReportUrl}">link to report</a></p>
	</body>
</html>
