#!/bin/bash

# Command to repeat
command_to_repeat1="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test1_local.txt"

command_to_repeat2="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test2_local.txt"


command_to_repeat4="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test4_local.txt"


command_to_repeat8="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test8_local.txt"


command_to_repeat16="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test16_local.txt"


command_to_repeat32="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test32_local.txt"


command_to_repeat64="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test64_local.txt"


command_to_repeat128="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test128_local.txt"


command_to_repeat256="curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'loaddata'}); \" http://192.168.0.72:8080/bigdawg/query/ -w '{\n\t\"content_type\": \"%{content_type}\",\n\t\"filename_effective\": \"%{filename_effective}\",\n\t\"ftp_entry_path\": \"%{ftp_entry_path}\",\n\t\"http_code\": \"%{http_code}\",\n\t\"http_connect\": \"%{http_connect}\",\n\t\"http_version\": \"%{http_version}\",\n\t\"local_ip\": \"%{local_ip}\",\n\t\"local_port\": \"%{local_port}\",\n\t\"num_connects\": \"%{num_connects}\",\n\t\"num_redirects\": \"%{num_redirects}\",\n\t\"proxy_ssl_verify_result\": \"%{proxy_ssl_verify_result}\",\n\t\"redirect_url\": \"%{redirect_url}\",\n\t\"remote_ip\": \"%{remote_ip}\",\n\t\"remote_port\": \"%{remote_port}\",\n\t\"scheme\": \"%{scheme}\",\n\t\"size_download\": \"%{size_download}\",\n\t\"size_header\": \"%{size_header}\",\n\t\"size_request\": \"%{size_request}\",\n\t\"size_upload\": \"%{size_upload}\",\n\t\"speed_download\": \"%{speed_download}\",\n\t\"speed_upload\": \"%{speed_upload}\",\n\t\"ssl_verify_result\": \"%{ssl_verify_result}\",\n\t\"time_appconnect\": \"%{time_appconnect}\",\n\t\"time_connect\": \"%{time_connect}\",\n\t\"time_namelookup\": \"%{time_namelookup}\",\n\t\"time_pretransfer\": \"%{time_pretransfer}\",\n\t\"time_redirect\": \"%{time_redirect}\",\n\t\"time_starttransfer\": \"%{time_starttransfer}\",\n\t\"time_total\": \"%{time_total}\",\n\t\"url_effective\": \"%{url_effective}\"\n}\n'>>test256_local.txt"

# Delay between command executions (in seconds)
delay_seconds=0

# Number of times to repeat

outerloop=6

for ((j=0; j<$outerloop; j++)); do
# Loop to execute the command multiple times with a delay
repeat_count=1
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat1"
    eval "$command_to_repeat1"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=2
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat2"
    eval "$command_to_repeat2"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=4
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat4"
    eval "$command_to_repeat4"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=8
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat8"
    eval "$command_to_repeat8"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=16
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat16"
    eval "$command_to_repeat16"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=32
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat32"
    eval "$command_to_repeat32"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=64
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat64"
    eval "$command_to_repeat64"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=128
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat128"
    eval "$command_to_repeat128"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
repeat_count=256
for ((i=1; i<=$repeat_count; i++)); do
    echo "Executing command $i: $command_to_repeat256"
    eval "$command_to_repeat256"

    # Check if it's the last iteration to avoid an extra delay
    if [ $i -ne $repeat_count ]; then
        sleep $delay_seconds
    fi
done
done

