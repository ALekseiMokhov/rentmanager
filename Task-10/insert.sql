USE senla;
INSERT INTO product (maker,model,type)
VALUES
('IBM','IBM5150','pc'),
('IBM','IBM PS','pc'),
('IBM','IBM APTIVA','pc'),
('IBM','IBM compact','laptop'),
('ELECTRON','ELECTRON3000','pc'),
('ELECTRON','ELECTRON-print','printer'),
('TOSHIBA','TOSHIBA 512se','pc'),
('APPLE','APPLE-1000','pc'),
('DELL','DELL 900','laptop'),
('DELL','DELL 200','laptop'),
('TOSHIBA','TOSHIBA P2','laptop'),
('HP','HP 23','laptop'),
('ACER','ACER 2000','laptop'),
('HP','HP 33','printer'),
('HP','HP C','printer'),
('PANASONIC','PANASONIC kx1500','printer'),
('CANON','CANON 2','printer')
ON DUPLICATE KEY UPDATE maker = maker;

INSERT INTO pc (model,speed,ram,hd,cd,price)
VALUES
('IBM5150', 256, 128, 12.5,'24X', 2984.53),
('ELECTRON3000', 64, 16, 8.0,'12X', 1033.4),
('APPLE-1000', 512, 256, 21.1,'12X', 407.5 ),
('TOSHIBA 512se', 512, 256, 12.5,'12X', 782.0),
('IBM PS', 512, 256, 27.2,'8X', 399),
('IBM APTIVA', 1024, 512, 31.0,'32X', 5027.8)
ON DUPLICATE KEY UPDATE model = model;

INSERT INTO laptop (model,speed,ram,hd,screen,price)
VALUES
('DELL 900', 128, 64, 130.0, 12, 1003.5),
('DELL 200', 64, 32, 65.0, 11.2, 707.4),
('IBM compact', 1024, 512, 300.0, 15.1, 3707.4),
('TOSHIBA P2', 128, 64, 120.0, 10, 1200),
('HP 23', 64, 32, 80.0, 10, 780),
('ACER 2000', 32, 64, 150, 11, 440)
ON DUPLICATE KEY UPDATE model = model;

INSERT INTO printer (model,color,type,price)
VALUES
('HP 33', 'y', 'Jet', 430),
('HP C', 'y', 'Laser', 800.5),
('ELECTRON-print', 'n', 'Matrix', 100.4),
('PANASONIC kx1500', 'n', 'Matrix', 205.8),
('CANON 2', 'y', 'Laser', 1203)
ON DUPLICATE KEY UPDATE model = model;
