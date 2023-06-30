create database mistore;
use mistore;
 
/*1.地址信息*/
CREATE TABLE `addrinfo` (
                            `ano` varchar(100) primary key NOT NULL,/*地址编号*/
                            `mno` int(11) DEFAULT NULL,/* 用户编号，登录用户 */
                            `name` varchar(100)  NOT NULL,/* 收件人 */
                            `tel` varchar(15)  NOT NULL,/* 电话号码 */
                            `province` varchar(100)  NOT NULL,/* 省 */
                            `city` varchar(100)  NOT NULL,/* 市 */
                            `area` varchar(100)  NOT NULL,/* 区 */
                            `addr` varchar(100)  NOT NULL,/* 详细地址 */
                            `flag` int(11) DEFAULT NULL,/* */
                            `status` int(11) DEFAULT NULL/* */
);
 
INSERT INTO `addrinfo` VALUES
('1590052579423',1,'周海军','15096098010','湖南省','衡阳市','珠晖区','美的梧桐庄园',0,1),
('1590053041766',1,'源辰','15096098010','湖南省','衡阳市','珠晖区','衡花路18号湖南工学院',1,1),
('1590053187661',1,'周天','1509608011','湖南省','衡阳市','珠晖区','南华大学',0,1);

/*2.管理员信息*/
CREATE TABLE `admininfo`(
                            `aid` int(11) primary key NOT NULL AUTO_INCREMENT,
                            `aname` varchar(100) COLLATE utf8_bin NOT NULL,
                            `pwd` varchar(100) COLLATE utf8_bin NOT NULL,
                            `tel` varchar(15) COLLATE utf8_bin DEFAULT NULL,
                            `status` int(11) DEFAULT NULL
);
 
INSERT INTO `admininfo` VALUES
(1,'navy','c8837b23ff8aaa8a2dde915473ce0991','15096098088',1);
INSERT INTO `admininfo` VALUES (100,'a','0cc175b9c0f1b6a831c399e269772661','15096098088',1);


/*4.商品信息*/
CREATE TABLE `goodsinfo` (
                             `gno` int(11) primary key NOT NULL AUTO_INCREMENT,
                             `gname` varchar(100)  NOT NULL,
                             `tno` int(11) DEFAULT NULL,
                             `price` decimal(8,2) DEFAULT NULL,
                             `intro` varchar(200)  DEFAULT NULL,
                             `balance` int(11) DEFAULT NULL,
                             `pics` varchar(500)  DEFAULT NULL,
                             `qperied` varchar(50)  DEFAULT NULL,
                             `descr` varchar (500),
                             `status` int(11) DEFAULT NULL
);
 
INSERT INTO `goodsinfo` VALUES (1, 'Redmi K50 至尊版 8GB+256GB', '102', '3299.00', '反馈用户最新款', '3099', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/55098d0d1d85c73c6bfc07e88fb9a3a3.png',  '2020/09/01', 0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (2, '小米 12 8GB+256GB', '101', '3699.00', '反馈用户最新款', '3699', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/149a55c7db655a57bce72528466c6bbc.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (3, '小米 11 青春版 8GB+128GB', '101', '1999.00', '反馈用户最新款', '1599', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/b36954e448a48cc3c61fb2d020d20f63.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (4, '小米 12S 12GB+256GB', '101', '4699.00', '反馈用户最新款', '4399', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202207012022_19bbddb6b35c3828f8b53f450c1519a3.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (5, '小米 12X 8GB+128GB', '101', '2999.00', '反馈用户最新款', '2699', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/4257d435e77ec82bb6922e83b9bf5bcc.png',  '2020/09/01', 0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (6, '小米 10S 8GB+128GB', '101', '2699.00', '反馈用户最新款', '2399', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/b3d41aa1a9cc51c3c943d5ed9fd70101.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (7, 'Redmi Note 11 5G 4GB+128GB', '102', '1199.00', '反馈用户最新款', '1199', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/d13f434be6b12bd9b5486247425eca6f.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (8, 'Redmi Note 11T Pro 12GB+256GB', '102', '2399.00', '反馈用户最新款', '2099', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205221513_5a46ea12800c7d02b9a1c9a050e2a66e.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (9, 'Redmi 10A 4GB+64GB', '102', '699.00', '反馈用户最新款', '699', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/e813ca2ccc6708077b17dacbdf1d1465.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (10, 'Redmi Note 11T Pro+ 8GB+512GB', '102', '2499.00', '反馈用户最新款', '2299', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205221513_5a46ea12800c7d02b9a1c9a050e2a66e.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (11, 'Redmi K50 8GB+128GB', '102', '2399.00', '反馈用户最新款', '2299', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/55098d0d1d85c73c6bfc07e88fb9a3a3.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (12, 'Redmi Note 11 4G 4GB+128GB', '102', '999.00', '反馈用户最新款', '949', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/e690d968320c356b7a09c8f63bc6ef8d.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (13, 'Redmi Note 11 Pro+ 6GB+128GB', '102', '1999.00', '反馈用户最新款', '1999', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/4b96f308ef704cf93068c41233925364.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (14, '小米 11 青春活力版 8GB+128GB', '101', '1999.00', '反馈用户最新款', '1599', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0593c1e9e06a125b9e22c1041aa0a85d.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (15, '小米 Civi 2 8GB+256GB', '101', '2499.00', '反馈用户最新款', '2499', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202209261918_25824e46b0048eeb4c7c5512bda46956.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (16, 'Redmi Note 12 Pro+ 12GB+256GB', '102', '2399.00', '反馈用户最新款', '2299', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202210262023_25a7386bbd53404366659b64c8cc4b35.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (17, 'Redmi Note 12 5G 4GB+128GB', '102', '1199.00', '反馈用户最新款', '1199', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202210262033_d865bffadb8d831a749a65a4bf00eeb3.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (18, '小米 12 Pro天玑版 8GB+128GB', '101', '3999.00', '反馈用户最新款', '2999', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202207011841_084ed41d67f248677914605b73faf582.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (19, '小米 Civi 8GB+128GB', '101', '2299.00', '反馈用户最新款', '2099', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/540458f4ee2b99df2784f3a6021866ce.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (20, '小米 12S Pro 12GB+256GB', '101', '5399.00', '反馈用户最新款', '5099', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202207012000_0b9df066c110f201154013ac373df1d9.png', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (21, '小米电视EA55 2022款55英寸 黑色', '103', '2099.00', '反馈用户最新款', '1199', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/b516f0fea1d073f3e896bde881b24813.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (22, '小米电视EA65 2022款65英寸 黑色', '103', '2899.00', '反馈用户最新款', '1799', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/03b50609fe96ae56344760149742e045.png', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (23, '小米电视EA43 2022款43英寸 黑色', '103', '1399.00', '反馈用户最新款', '699', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/b8b07e5d554cf188b18cb37367b191b3.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (24, '小米电视EA75 2022款75英寸 黑色', '103', '4199.00', '反馈用户最新款', '2799', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/6e238436a75366c62a06d3c994546098.jpg',  '2020/09/01', 0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (25, '小米电视ES75 2022款75英寸 黑色', '103', '5999.00', '反馈用户最新款', '4999', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/fd011d5da0907570c4504d57f7736961.jpg', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (26, 'Redmi MAX 86英寸 深青色', '103', '7999.00', '反馈用户最新款', '5899', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/5f8be2fa1f60f81a0cfbfa3f6e0a5723.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (27, '小米电视EA58 2022款58英寸 黑色', '103', '1899.00', '反馈用户最新款', '1399', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205172334_e09d01f2ac097815342c53456c154c94.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (28, 'Redmi X65 2022款 65英寸 蓝色', '103', '3999.00', '反馈用户最新款', '2699', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/3332ab1816381f6c835b7ea04b3d7ec2.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (29, '小米电视EA70 2022款70英寸 黑色', '103', '3299.00', '反馈用户最新款', '2269', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202112221103_39d5f9447508a0e4d4a0c18d3e625462.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (30, '小米电视EA60 2022款60英寸 黑色', '103', '2099.00', '反馈用户最新款', '1699', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205172345_362b9177a149ecce6af907565a7f7f52.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (31, 'RedmiBook Pro 14 锐龙版', '104', '4599.00', '反馈用户最新款', '3799', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/01d9571051a91e540e5c644fa60cc858.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (32, 'Redmi G 游戏本 2022', '104', '7499.00', '反馈用户最新款', '6299', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/50b73aeedeeb27392a15d9d479e5d793.png',  '2020/09/01', 0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (33, 'Redmi Book Pro 14 2022', '104', '5399.00', '反馈用户最新款', '4499', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/e3c300330221ad788569e4fb1e6f932f.jpg', '2020/09/01', 0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (34, 'RedmiBook Pro 15 2022 锐龙版', '104', '5499.00', '反馈用户最新款', '4999', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0f8abb2bc3947c4679e3f1b2aafc28c0.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (35, 'Redmi G Pro 游戏本', '104', '8999.00', '反馈用户最新款', '7999', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/f0408c24b677c7fe8b3cc402ec062334.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (36, 'Xiaomi Book Air 13', '104', '5999.00', '反馈用户最新款', '5199', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202210261144_d2b9ed45a4364c701d2d15f1655808b1.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (37, 'Redmi G Pro 游戏本 锐龙版 R7', '104', '8799.00', '反馈用户最新款', '7799', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/f0408c24b677c7fe8b3cc402ec062334.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (38, '小米笔记本 Pro 15 增强版', '104', '6499.00', '反馈用户最新款', '5799', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/4d8079f939bef922a9dae3a70d26b739.jpg', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (39, 'RedmiBook Pro 14 增强版', '104', '4999.00', '反馈用户最新款', '4499', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/2cdad2ced355841f856bf1e592937144.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (40, 'Redmi G 游戏本 锐龙版 R5', '104', '7299.00', '反馈用户最新款', '6299', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202209071149_f0408c24b677c7fe8b3cc402ec062334.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (41, '米家波轮洗衣机 10kg', '105', '1099.00', '反馈用户最新款', '899', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202211062045_0c3a843542a21a3c2fdd8a688702e153.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (42, '米家吸尘器2', '105', '199.00', '反馈用户最新款', '189', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202209281924_cbee39fdc693a0dc73239b164111b05a.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (43, '米家智能空气炸烤箱 30L', '105', '899.00', '反馈用户最新款', '749', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202209161102_f850e222a57495748e195022188597a3.jpg',  '2020/09/01', 0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (44, '米家加湿器 2', '105', '99.00', '反馈用户最新款', '99', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202207251549_b08f2a116971c49853718131d214993e.jpg', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (45, '米家纯净式智能加湿器 2 Lite 滤芯', '105', '69.00', '反馈用户最新款', '69', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202211091008_75925b258bd9c42e37abbfc1758b94d0.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (46, '米家负离子吹风机 H301', '105', '199.00', '反馈用户最新款', '199', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202210311030_6ece93f049be836734168bbcecd79d00.png', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (47, '米家声波电动牙刷T500C', '105', '249.00', '反馈用户最新款', '199', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/bc00c5d4c49ac047f110525efd7966d7.jpg', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (48, '米家电磁炉青春版', '105', '199.00', '反馈用户最新款', '179', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202206291533_8503fdd16b3865c83d52bece4a58c1c8.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (49, '米家冰箱 对开门 502L冰晶版', '105', '3299.00', '反馈用户最新款', '2799', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202204241528_2cdbca34d0fe450fe6bf3b0e3671e5d5.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (50, '米家负离子速干吹风机 H300', '105', '149.00', '反馈用户最新款', '129', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/86122f0706bea7bf11dde55a6f32c032.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (51, 'Populele 2 智能尤克里里', '106', '599.00', '反馈用户最新款', '479', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/3cd3238c2a7dd7fae457bc466a9f3771.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (52, '小米手环7', '106', '249.00', '反馈用户最新款', '249', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205240036_9a8fbf274c0d419c5a0a49ef67cdd7c8.png?https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205240036_9a8fbf274c0d419c5a0a49ef67cdd7c8.png?https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205240036_9a8fbf274c0d419c5a0a49ef67cdd7c8.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (53, '小米巨能写中性笔10支装', '106', '9.99', '反馈用户最新款', '10', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/57433a4b991b2a2ddc889f2d8d434655.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (54, '小米智能摄像机 云台版2K', '106', '199.00', '反馈用户最新款', '199', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202207281747_c1d5b84c419edf2a7e62b6bdb3936c1b.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (55, '小米巨能写多彩中性笔', '106', '9.99', '反馈用户最新款', '10', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1607063604.51577978.jpg', '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (56, '小米WiFi放大器 Pro', '106', '69.00', '反馈用户最新款', '69', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-miapp-a1/9085cddd-530a-0bf0-8b09-74e96fa97cc9.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (57, '米家台灯Lite', '106', '99.00', '反馈用户最新款', '99', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/9d0dfa2d1143f02e819fbee357b985ea.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (58, 'CyberDog 仿生四足机器人', '106', '9999.00', '反馈用户最新款', '9999', 'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/7e8ad7421796730fd980983aa9e7c1d9.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (59, '小米手环7 NFC版', '106', '299.00', '反馈用户最新款', '299', 'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202205240036_9a8fbf274c0d419c5a0a49ef67cdd7c8.png',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');
INSERT INTO `goodsinfo` VALUES (60, '小米米家液晶小黑板', '106', '49.00', '反馈用户最新款', '49', 'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1618813977.20943291.jpg',  '2020/09/01',  0x3C703EE5BE88E5A5BDE794A83C2F703E0D0A, '1');

/*5.商品类型*/
CREATE TABLE `goodstype`(
                            `tno` int(11) primary key NOT NULL AUTO_INCREMENT,/*类型编号*/
                            `tname` varchar(100)  NOT NULL,/*类型名*/
                            `status` int(11) DEFAULT NULL/* */
);
INSERT INTO `goodstype` VALUES (101,'小米手机',1),
                               (102,'Redmi红米',1),
                               (103,'电视',1),
                               (104,'笔记本',1),
                               (105,'家电',1),
                               (106,'智能硬件',1);
 
/*6.用户信息*/
create table memberinfo(
                           `mno` int(11)primary key NOT NULL AUTO_INCREMENT,
                           `nickName` varchar(100) DEFAULT NULL,
                           `pwd` varchar(100) NOT NULL,
                           `tel` varchar(15) DEFAULT NULL,
                           `email` varchar(100) NOT NULL,
                           `photo` varchar(100) DEFAULT NULL,
                           `regDate` varchar(50) DEFAULT NULL,
                           `status` int(11) DEFAULT NULL
);
 
/*7.订单信息*/
CREATE TABLE `orderinfo`
(
    `ono`     varchar(100) primary key NOT NULL,/*订单编号*/
    `odate`   varchar(50)  DEFAULT NULL,/*订单时间*/
    `ano`     varchar(100) DEFAULT NULL,/*地址编号*/
    `sdate`   varchar(50)  DEFAULT NULL,
    `rdate`   varchar(50)  DEFAULT NULL,
    `status`  int(11)   DEFAULT NULL,
    `price`   varchar(50)  DEFAULT NULL,/*价格*/
    `invoice` int(11)   DEFAULT NULL
);
 
INSERT INTO `orderinfo` VALUES ('75813a5fed5a4369ad3a14c2c5ae7ed3','2020-09-03 14:55:27','1590053041767',NULL,NULL,1,5009.00,0),
                               ('75813a5fed5a4369ad3a14c2c5ae7ed2','2020-01-03 14:55:27','1590053041767',NULL,NULL,1,5009.00,0),
                               ('75813a5fed5a4369ad3a14c2c5ae7ed1','2020-06-03 14:55:27','1590053041767',NULL,NULL,1,5009.00,0),
                               ('75813a5fed5a4369ad3a14c2c5ae7ed4','2020-01-03 14:55:27','1590053041767',NULL,NULL,1,5009.00,0),
                               ('75813a5fed5a4369ad3a14c2c5ae7ed5','2020-02-03 14:55:27','1590053041767',NULL,NULL,1,2009.00,0),
                               ('75813a5fed5a4369ad3a14c2c5ae7ed6','2020-03-03 14:55:27','1590053041767',NULL,NULL,1,8009.00,0),
                               ('75813a5fed5a4369ad3a14c2c5ae7ed7','2020-04-03 14:55:27','1590053041767',NULL,NULL,1,3009.00,0),
                               ('78145f7f49bb407286132fde0c339667','2020-12-08 16:45:36','1590053187661',NULL,NULL,1,8008.00,0),
                               ('78e389e8206b465696de969a4b12a331','2020-05-08 16:19:19','1590053041766',NULL,NULL,1,18004.00,0),
                               ('b22b61764547419d816fecdf928bbf11','2020-12-08 17:37:42','1590053187661',NULL,NULL,1,5009.00,0),
                               ('b555af22dcf24ac0859ada9e33f47be5','2020-10-18 19:07:13','1590053041766',NULL,NULL,1,9007.00,0),
                               ('f31decbb8f594f8d9e8d075681ac6029','2020-12-08 16:18:28','1590053041766',NULL,NULL,1,15007.00,0);


/*8.订单商品信息*/
CREATE TABLE `orderiteminfo`(
                                `ino`    int(11) primary key NOT NULL AUTO_INCREMENT,/*物品号*/
                                `ono`    varchar(100)  DEFAULT NULL,/*订单编号*/
                                `gno`    int(11)     DEFAULT NULL,/*商品编号*/
                                `nums`   int(11)      DEFAULT NULL,/*数量*/
                                `price`  varchar (50)    DEFAULT NULL,/*价格*/
                                `status` int(11)     DEFAULT NULL/* */
);
 
insert into orderiteminfo values  (10,'75813a5fed5a4369ad3a14c2c5ae7ed1',12,1,4999.00,1),
                                  (11,'75813a5fed5a4369ad3a14c2c5ae7ed2',28,1,14999.00,1),
                                  (12,'75813a5fed5a4369ad3a14c2c5ae7ed4',34,1,14999.00,1),
                                  (13,'75813a5fed5a4369ad3a14c2c5ae7ed5',12,1,4999.00,1),
                                  (14,'75813a5fed5a4369ad3a14c2c5ae7ed6',28,1,14999.00,1),
                                  (15,'75813a5fed5a4369ad3a14c2c5ae7ed7',34,1,14999.00,1),
                                  (1,'b555af22dcf24ac0859ada9e33f47be5',13,3,2999.00,1),
                                  (2,'75813a5fed5a4369ad3a14c2c5ae7ed2',12,1,4999.00,1),
                                  (3,'f31decbb8f594f8d9e8d075681ac6029',12,3,4999.00,1),
                                  (4,'78e389e8206b465696de969a4b12a331',13,6,2999.00,1),
                                  (5,'78145f7f49bb407286132fde0c339667',12,1,4999.00,1),
                                  (6,'78145f7f49bb407286132fde0c339667',13,1,2999.00,1),
                                  (8,'b22b61764547419d816fecdf928bbf11',12,1,4999.00,1),
                                  (9,'b555af22dcf24ac0859ada9e33f47be5',22,3,2999.00,1);