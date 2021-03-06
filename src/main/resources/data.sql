INSERT INTO chat_bot.`option` (id, type, name) VALUES (1, 'CRUST', 'Đế mỏng giòn');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (2, 'SIZE', 'Size 7 inch = 89,000đ');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (3, 'SIZE', 'Size 9 inch =  169,000đ');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (4, 'SIZE', 'Size 12 inch =  259,000đ');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (5, 'EXTRAS', 'Thêm phô mai 9" = 10,000đ ');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (6, 'EXTRAS', 'Gấp đôi phô mai 9" = 20,000đ');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (7, 'EXTRAS', 'Gấp ba phô mai 9" = 30,000đ');
INSERT INTO chat_bot.`option` (id, type, name) VALUES (8, 'OUTER_CRUST', 'Viền phô mai 9" = 49,000đ');

INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 1, 0);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 2, 89000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 3, 169000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 4, 259000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 5, 10000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 6, 20000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 7, 30000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (1, 8, 49000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 1, 0);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 2, 89000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 3, 169000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 4, 259000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 5, 10000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 6, 20000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 7, 30000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (2, 8, 49000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 1, 0);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 2, 89000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 3, 169000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 4, 259000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 5, 10000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 6, 20000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 7, 30000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (3, 8, 49000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 1, 0);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 2, 89000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 3, 169000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 4, 259000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 5, 10000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 6, 20000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 7, 30000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (4, 8, 49000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 1, 0);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 2, 89000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 3, 169000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 4, 259000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 5, 10000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 6, 20000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 7, 30000);
INSERT INTO chat_bot.product_option_mapping (product_id, option_id, extra_fee) VALUES (5, 8, 49000);

INSERT INTO chat_bot.product (id, name, price, image_url) VALUES (1, 'Pizza Bò & Tôm Nướng Kiểu Mỹ - Surf & Turf', 89000, 'https://img.dominos.vn/Surf-_-turf.jpg');
INSERT INTO chat_bot.product (id, name, price, image_url) VALUES (2, 'Pizza Hải Sản Xốt Mayonnaise - Ocean Mania', 89000, 'https://img.dominos.vn/Ocean-mania.jpg');
INSERT INTO chat_bot.product (id, name, price, image_url) VALUES (3, 'Pizza Hải Sản Nhiệt Đới Xốt Tiêu - Pizzamin Sea', 89000, 'https://img.dominos.vn/Pizzaminsea.jpg');
INSERT INTO chat_bot.product (id, name, price, image_url) VALUES (4, 'Pizza Bánh Xèo Nhật - Okonomiyaki', 89000, 'https://img.dominos.vn/H%C3%ACnh%2Bproduct%2Bg%E1%BA%AFn%2Btag%2BNEW-01.jpg');
INSERT INTO chat_bot.product (id, name, price, image_url) VALUES (5, 'Half Half', 89000, 'https://img.dominos.vn/Haft-haft.jpg');