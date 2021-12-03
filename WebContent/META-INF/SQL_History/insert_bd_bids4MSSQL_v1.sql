INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('root','root','root','root@root.fr','','root','root','root','prhRGjOQDBM=',0,1);
INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('toto','toto','toto','toto@toto.fr','','toto','toto','toto','adXEDYhkhQU=',0,0);
INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('bod','sébastien','baudin','bod@toto.fr','','rue','99999','ville','rgy83BXGdV4=',0,1);
INSERT INTO [dbo].[USERS] ([pseudo],[name],[firstName],[email],[telephone],[street],[zipCode],[town],[pwd],[credit],[isAdmin])
VALUES ('eni','eni','eni','eni@toto.fr','','rue','99999','ville','PF2IU6Eqgpc=',0,0);

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

VALUES ('DELL inspiron 6700', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-6, getdate()-1, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6800', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-7, getdate()-1, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 6900', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-8, getdate()+3, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7000', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-9, getdate()+3, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7100', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-10, getdate()+4, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7200', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-11, getdate()+5, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7300', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-12, getdate()+6, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7400', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-13, getdate()+7, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7500', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-14, getdate()+8, 250,null,1,1,'En cours');
INSERT INTO [dbo].[BID_ITEMS]([name],[description],[dateStart],[dateEnd],[priceSeller],[priceBuyer],[userIdSeller],[cateId],[status])
VALUES ('DELL inspiron 7600', 'ordinateur 4Go de RAM, disque SSD 120Go, écran 17 pouce, windows installé', getdate()-15, getdate()+9, 250,null,1,1,'En cours');


--2 Ended bids
INSERT INTO [dbo].[BIDS]([userIdBuyer],[itemId],[dateCreated],[amount]) VALUES (3,6,GETDATE()-2,300);
INSERT INTO [dbo].[BIDS]([userIdBuyer],[itemId],[dateCreated],[amount]) VALUES (3,7,GETDATE()-2,400);

--1 new sell with 2 bids
INSERT INTO [dbo].[BIDS]([userIdBuyer],[itemId],[dateCreated],[amount]) VALUES (2,8,GETDATE()-2,200);
INSERT INTO [dbo].[BIDS]([userIdBuyer],[itemId],[dateCreated],[amount]) VALUES (3,9,GETDATE()-2,250);
