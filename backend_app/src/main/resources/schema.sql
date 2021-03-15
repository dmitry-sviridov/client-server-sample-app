DROP TABLE IF EXISTS t_shop_items;

CREATE TABLE t_shop_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    descr VARCHAR(255) NOT NULL,
    is_on_sale BOOL NULL_TO_DEFAULT,
    price DOUBLE NOT NULL,
    image_url VARCHAR(255) NOT NULL
);