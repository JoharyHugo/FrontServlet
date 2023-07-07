mkdir temp
jar -cf ./myFramework.jar  -C ./FrontServlet/build/web/WEB-INF/classes/ etu2021
sudo chmod 777 myFramework.jar
mv ./myFramework.jar ./Test/build/web/WEB-INF/lib
find ./Test/src/java/ -name "*.java" | xargs -I {} javac -cp ./Test/build/web/WEB-INF/lib/myFramework.jar -d  ./Test/build/web/WEB-INF/classes/ {} 
sudo chmod 777 -R temp
cp -r ./Test/build/web/* ./temp
sudo chmod 777 -R temp
cd ./temp/    
jar -cf ./myWebApp.war *  
sudo chmod 777 myWebApp.war
mv ./myWebApp.war /home/johary/"ITU TP"/S4/"TP Mr Naina"
cd ..
cp ./myWebApp.war /opt/tomcat/apache-tomcat-9.0.71/webapps
rm -r ./temp



