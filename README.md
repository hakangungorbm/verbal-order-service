## verbal-order-service

Medline olay onay ve takip sistemi(Sözel Order, Tetkik, Panik Değer)

http://localhost:8082/swagger-ui/index.html

## DEPLOY işlemi

Mevcut deploy olmuş uygulamalar

http://localhost:8082/manager/

adresi kullanılarak undeploy yapılır. Sonra intellij yardımıyla derlenerek  oluşturulan war dosyaları  C:\apache-tomcat-9.0.62\webapps  dizinine kopyalanır ve tekrar yine manager ekranından start edilir.

## Kurulumlar
**Docker Kurulumu**



1. Docker kurulum dosyası indirilir: https://www.docker.com/products/docker-desktop

2. Kurulum tamamlanır.

3. Docker ikonuna sağ tıklanarak Switch to Windows Containers yazdığı doğrulanır. Switch to Linux Container yazıyorsa tıklanılarak Linux container'a geçiş sağlanır.

4. Yine sağ tıklayıp Dashboard denilerek dashboard açılır.

5. Docker Engine konfigürasyonunda experimental değeri true olarak değişitirilir. Apply & Restart denilerek ayarlar uygulanır.

**Geliştirme Ortamı Postgres Kurulumu**

docker run --name postgres-instance --publish=5432:5432 -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres:13.2

**Aşağıdaki Bilgiler ile bağlantı kurulur**
- jdbc:postgresql://localhost:5432/postgres

- username: postgres

- password: postgres

- database: postgres


**Mikroservis bazlı veritabanlarını oluşturmak için uygun create database scriptleri çalıştırılır:**

- create database medlineverbalorder;


etc.

**Geliştirme Ortamı Redis Kurulumu**

docker run --name redis-instance -p 26379:6379 -d redis
