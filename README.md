# spring-demo
## redis
- nothing
## zookeeper
- nothing
## storm
- nothing
## solr
- nothing
## SPI
- nothing
## JUC
- nothing
## CGLIB
	- nothing
## LTS
	- nothing
## QuartzJob
	- nothing
## Spring redis session
	- nothing
	```
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>(){

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					System.out.println("---init-channel");
					ch.pipeline()
					.addLast(new TimeEncode())
					.addLast(new DiscardServerHandler());
				}
				
			})
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE,true);
			
			
			ChannelFuture future = bootstrap.bind(port).sync();   // 绑定端口，开始接收进来的连接
			
			 // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
			future.channel().closeFuture().sync();
			
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	```
	nothing3
## i18n
	- nothing
## RateLimiter
	- nothing
## HttpUnit
	- nothing
## Proxy
	- nothing
## Jsoup
	- nothing
## javassist
	- nothing
## Netty
- Reference
	- https://waylau.com/essential-netty-in-action/GETTING%20STARTED/The%20What%20and%20Why%20of%20Bootstrapping.html
	- https://waylau.com/netty-4-user-guide/Getting%20Started/Shutting%20Down%20Your%20Application.html

 
