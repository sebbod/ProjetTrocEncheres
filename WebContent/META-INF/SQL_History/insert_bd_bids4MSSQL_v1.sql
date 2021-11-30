INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('root','root','root','root@root.fr','','root','root','root','prhRGjOQDBM=',0,1);
INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('toto','toto','toto','toto@toto.fr','','toto','toto','toto','adXEDYhkhQU=',0,0);
INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('bod','sébastien','baudin','toto@toto.fr','','rue','99999','ville','rgy83BXGdV4=',0,1);


INSERT INTO [dbo].[BID_ITEM_CATEGORIES] ([libelle]) VALUES ('Informatique');
INSERT INTO [dbo].[BID_ITEM_CATEGORIES] ([libelle]) VALUES ('Ameublement');
INSERT INTO [dbo].[BID_ITEM_CATEGORIES] ([libelle]) VALUES ('Vêtemtent');
INSERT INTO [dbo].[BID_ITEM_CATEGORIES] ([libelle]) VALUES ('Sport&Loisirs');


INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6200', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'Créée');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6300', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'Annulée');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6400', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6500', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'Terminée');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6600', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'Retirée');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6700', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6800', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6900', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7000', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7100', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7200', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7300', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7400', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7500', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7600', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate(), getdate()+30, 250,null,1,1,'En cours');



