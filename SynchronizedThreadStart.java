package bigdawg.bigdawg;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
public class SynchronizedThreadStart {
    public static void main(String[] args) {
        int n = 1; // Number of threads to create

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);

        for (int i = 1; i <= n; i++) {
            Thread thread = new Thread(new Worker(startSignal, doneSignal));
            thread.setName(String.valueOf(i));
            thread.start(); // Start each thread
        }

        // Release the threads to start simultaneously
        startSignal.countDown();

        try {
            // Wait for all threads to finish
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have started and completed.");
    }
}

class Worker1 implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    int counter=0;
    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();

            startSignal.await(); // Wait for the signal to start
            // Your thread's work goes here
            System.out.println("Thread started.");
                
            //String curlCommand = "curl -X POST -d \"bdarray(filter(TABLE_NAME,i>0));\" http://localhost:8080/bigdawg/query/>>a"+Thread.currentThread().getName()+".txt";
            //1.Scidb inside Scidb
            String curlCommand1 = "curl -X POST -d \"bdarray(filter(TABLE_NAME,i>0));\""+" http:/IP_ADDRESS:8080/bigdawg/query/ -w " +
                    "'{'\"content_type\":\"%{content_type}\",\"filename_effective\":\"%{filename_effective}\"," +
                    "\"ftp_entry_path\":\"%{ftp_entry_path}\",\"http_code\":\"%{http_code}\",\"http_connect\":\"%{http_connect}\"," +
                    "\"http_version\":\"%{http_version}\",\"local_ip\":\"%{local_ip}\",\"local_port\":\"%{local_port}\"," +
                    "\"num_connects\":\"%{num_connects}\",\"num_redirects\":\"%{num_redirects}\"," +
                    "\"proxy_ssl_verify_result\":\"%{proxy_ssl_verify_result}\",\"redirect_url\":\"%{redirect_url}\"," +
                    "\"remote_ip\":\"%{remote_ip}\",\"remote_port\":\"%{remote_port}\",\"scheme\":\"%{scheme}\"," +
                    "\"size_download\":\"%{size_download}\",\"size_header\":\"%{size_header}\"," +
                    "\"size_request\":\"%{size_request}\",\"size_upload\":\"%{size_upload}\"," +
                    "\"speed_download\":\"%{speed_download}\",\"speed_upload\":\"%{speed_upload}\"," +
                    "\"ssl_verify_result\":\"%{ssl_verify_result}\",\"time_appconnect\":\"%{time_appconnect}\"," +
                    "\"time_connect\":\"%{time_connect}\",\"time_namelookup\":\"%{time_namelookup}\"," +
                    "\"time_pretransfer\":\"%{time_pretransfer}\",\"time_redirect\":\"%{time_redirect}\"," +
                    "\"time_starttransfer\":\"%{time_starttransfer}\",\"time_total\":\"%{time_total}\"," +
                    "\"url_effective\":\"%{url_effective}\"'}>>a"+Thread.currentThread().getName()+".txt";
            
            
            //2.Scidb moved data psql storage
            String curlCommand = "curl -X POST -d \"bdrel(select * from TABLE_NAME;)\""+" http:/IP_ADDRESS:8080/bigdawg/query/ -w " +
                    "'{'\"content_type\":\"%{content_type}\",\"filename_effective\":\"%{filename_effective}\"," +
                    "\"ftp_entry_path\":\"%{ftp_entry_path}\",\"http_code\":\"%{http_code}\",\"http_connect\":\"%{http_connect}\"," +
                    "\"http_version\":\"%{http_version}\",\"local_ip\":\"%{local_ip}\",\"local_port\":\"%{local_port}\"," +
                    "\"num_connects\":\"%{num_connects}\",\"num_redirects\":\"%{num_redirects}\"," +
                    "\"proxy_ssl_verify_result\":\"%{proxy_ssl_verify_result}\",\"redirect_url\":\"%{redirect_url}\"," +
                    "\"remote_ip\":\"%{remote_ip}\",\"remote_port\":\"%{remote_port}\",\"scheme\":\"%{scheme}\"," +
                    "\"size_download\":\"%{size_download}\",\"size_header\":\"%{size_header}\"," +
                    "\"size_request\":\"%{size_request}\",\"size_upload\":\"%{size_upload}\"," +
                    "\"speed_download\":\"%{speed_download}\",\"speed_upload\":\"%{speed_upload}\"," +
                    "\"ssl_verify_result\":\"%{ssl_verify_result}\",\"time_appconnect\":\"%{time_appconnect}\"," +
                    "\"time_connect\":\"%{time_connect}\",\"time_namelookup\":\"%{time_namelookup}\"," +
                    "\"time_pretransfer\":\"%{time_pretransfer}\",\"time_redirect\":\"%{time_redirect}\"," +
                    "\"time_starttransfer\":\"%{time_starttransfer}\",\"time_total\":\"%{time_total}\"," +
                    "\"url_effective\":\"%{url_effective}\"'}>>a"+Thread.currentThread().getName()+".txt";
            
            //3.Scidb to psql
            String curlCommand5 = "curl -X POST -d \"bdrel(select * from bdcast( bdarray(filter(TABLE_NAME,i>15000)), tab"+Thread.currentThread().getName()+",'(COLUMNS LIST WITH DATATYPE)', relational))\""+" http://localhost:8080/bigdawg/query/ -w " +
                    "'{'\"content_type\":\"%{content_type}\",\"filename_effective\":\"%{filename_effective}\"," +
                    "\"ftp_entry_path\":\"%{ftp_entry_path}\",\"http_code\":\"%{http_code}\",\"http_connect\":\"%{http_connect}\"," +
                    "\"http_version\":\"%{http_version}\",\"local_ip\":\"%{local_ip}\",\"local_port\":\"%{local_port}\"," +
                    "\"num_connects\":\"%{num_connects}\",\"num_redirects\":\"%{num_redirects}\"," +
                    "\"proxy_ssl_verify_result\":\"%{proxy_ssl_verify_result}\",\"redirect_url\":\"%{redirect_url}\"," +
                    "\"remote_ip\":\"%{remote_ip}\",\"remote_port\":\"%{remote_port}\",\"scheme\":\"%{scheme}\"," +
                    "\"size_download\":\"%{size_download}\",\"size_header\":\"%{size_header}\"," +
                    "\"size_request\":\"%{size_request}\",\"size_upload\":\"%{size_upload}\"," +
                    "\"speed_download\":\"%{speed_download}\",\"speed_upload\":\"%{speed_upload}\"," +
                    "\"ssl_verify_result\":\"%{ssl_verify_result}\",\"time_appconnect\":\"%{time_appconnect}\"," +
                    "\"time_connect\":\"%{time_connect}\",\"time_namelookup\":\"%{time_namelookup}\"," +
                    "\"time_pretransfer\":\"%{time_pretransfer}\",\"time_redirect\":\"%{time_redirect}\"," +
                    "\"time_starttransfer\":\"%{time_starttransfer}\",\"time_total\":\"%{time_total}\"," +
                    "\"url_effective\":\"%{url_effective}\"'}>>a"+Thread.currentThread().getName()+".txt";
            
            //4.psql to scidb
            String curlCommand2 = "curl -X POST -d \"bdarray(scan(bdcast(bdrel(SELECT * FROM events6scidb limit1), newarray2, '<COLUMNS LIST WITH DATATYPE>[i=0:*,10000000,0]',array)));\""+" http://localhost:8080/bigdawg/query/ -w " +
                    "'{'\"content_type\":\"%{content_type}\",\"filename_effective\":\"%{filename_effective}\"," +
                    "\"ftp_entry_path\":\"%{ftp_entry_path}\",\"http_code\":\"%{http_code}\",\"http_connect\":\"%{http_connect}\"," +
                    "\"http_version\":\"%{http_version}\",\"local_ip\":\"%{local_ip}\",\"local_port\":\"%{local_port}\"," +
                    "\"num_connects\":\"%{num_connects}\",\"num_redirects\":\"%{num_redirects}\"," +
                    "\"proxy_ssl_verify_result\":\"%{proxy_ssl_verify_result}\",\"redirect_url\":\"%{redirect_url}\"," +
                    "\"remote_ip\":\"%{remote_ip}\",\"remote_port\":\"%{remote_port}\",\"scheme\":\"%{scheme}\"," +
                    "\"size_download\":\"%{size_download}\",\"size_header\":\"%{size_header}\"," +
                    "\"size_request\":\"%{size_request}\",\"size_upload\":\"%{size_upload}\"," +
                    "\"speed_download\":\"%{speed_download}\",\"speed_upload\":\"%{speed_upload}\"," +
                    "\"ssl_verify_result\":\"%{ssl_verify_result}\",\"time_appconnect\":\"%{time_appconnect}\"," +
                    "\"time_connect\":\"%{time_connect}\",\"time_namelookup\":\"%{time_namelookup}\"," +
                    "\"time_pretransfer\":\"%{time_pretransfer}\",\"time_redirect\":\"%{time_redirect}\"," +
                    "\"time_starttransfer\":\"%{time_starttransfer}\",\"time_total\":\"%{time_total}\"," +
                    "\"url_effective\":\"%{url_effective}\"'}>>a"+Thread.currentThread().getName()+".txt";
            
            
            
            
            //to store in a single file
            //String curlCommand = "curl -X POST -d \"bdarray(filter(TABLE_NAME,i>0)); \" http://localhost:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test1_middlePC.txt";

            for(int i=0;i<1;i++)
            {
            	try {
                    // Execute the curl command in a Linux environment
                    //for windows
                	ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);

                    //ProcessBuilder processBuil der = new ProcessBuilder("/bin/bash", "-c", curlCommand);
                    processBuilder.redirectErrorStream(true);
                    Process process = processBuilder.start();

                    // Wait for the process to complete
                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        System.out.println("Curl command executed successfully.");
                    } else {
                        System.err.println("Curl command failed with exit code: " + exitCode);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            	System.out.println("thread "+Thread.currentThread().getName() + " iteration "+ ++counter+" completed" );
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println(Thread.currentThread().getName()+" Thread execution time: " + executionTime + " milliseconds");
            System.out.println(Thread.currentThread().getName()+" Thread completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            doneSignal.countDown(); // Signal that this thread has completed
        }
    }
}

