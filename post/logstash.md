##logstash

Windows上关于logstash的使用 ：

最好下载 logstash-all-plugins版本的，这样省下了下载插件的烦恼。使用的时候，最好建立一个配置文件(例如logstash.conf)
这种格式的，然后启动的时候logstash -f logstash.conf(配置文件的名字可以随意)
eg:

````
input {
    redis {
        data_type => "pattern_channel"
        key => "logstash-*"
        host => "127.0.0.1"
        port => 6379
        threads => 5
    }
}

filter {
    grok {
        match => ["message",  "%{COMBINEDAPACHELOG}"]
    }
	date {
		match => ["logdate","dd/MMM/yyyy:HH:mm:ss Z"]
	}
}

output {
    elasticsearch {
        hosts => ["127.0.0.1:9200"]
        index => "logstash-%{type}-%{+YYYY.MM.dd}"
        document_type => "%{type}"
        workers => 1
        flush_size => 20000
        idle_flush_time => 10
        template_overwrite => true
    }
}
````

详情可以参考《ELK中文指南》这本在gitbook上的书。
如果想把MySQL的数据同步到ES上，可以这么写
````
input {
  jdbc {
    jdbc_driver_library => "mysql-connector-java-5.1.37-bin.jar"
    jdbc_driver_class => "com.mysql.jdbc.Driver"
    jdbc_connection_string => "jdbc:mysql://127.0.0.1:3306/cheche_core"
    jdbc_user => "root"
    jdbc_password => "13623652058"
    statement => "SELECT * from t_part_sku"
    jdbc_paging_enabled => "true"
    jdbc_page_size => "50000"
  }
}

filter {
    grok {
        match => ["message",  "%{COMBINEDAPACHELOG}"]
    }
	date {
		match => ["logdate","dd/MMM/yyyy:HH:mm:ss Z"]
	}
}

output {
    elasticsearch {
        hosts => ["127.0.0.1:9200"]
        index => "logstash-%{type}-%{+YYYY.MM.dd}"
        document_type => "%{type}"
        workers => 1
        flush_size => 20000
        idle_flush_time => 10
        template_overwrite => true
    }
}
````
然后打开 http://localhost:9200/_plugin/head/ 就可以看到logstash-%{type}-2016.03.10
                                              size: 5.29Mi (10.6Mi)
                                              docs: 8,344 (16,688)
了，然后启动kibnana,就可以看到我们导入的数据了。