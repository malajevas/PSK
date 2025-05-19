mvn clean package
copy "target\*.war" "D:\Payara\payara6\glassfish\domains\domain1\autodeploy\"
& "D:\Payara\payara6\bin\asadmin.bat" stop-domain
& "D:\Payara\payara6\bin\asadmin.bat" start-domain
# Start-Process "http://localhost:8080/javawebapp/index.xhtml"


$url = "http://localhost:8080/javawebapp/index.xhtml"
$maxWait = 30
$elapsed = 0

Write-Host "Waiting for $url to become available..."

while ($elapsed -lt $maxWait) {
    try {
        $response = Invoke-WebRequest -Uri $url -UseBasicParsing -TimeoutSec 2
        if ($response.StatusCode -eq 200) {
            Write-Host "App is ready! Launching browser..."
            Start-Process $url
            break
        }
    } catch {
        # Still waiting
    }
    Start-Sleep -Seconds 2
    $elapsed += 2
}

if ($elapsed -ge $maxWait) {
    Write-Host "Timed out waiting for the app. You can manually visit: $url"
}