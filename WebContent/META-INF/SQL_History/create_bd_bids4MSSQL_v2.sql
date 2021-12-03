--Ajout du status pour connaitre l'état de la vente d'un article [BID_ITEMS].[status]
--Liste des valeurs possible
--cree
--en cours
--terminee
--retiree
--annule

--trigger
--de cree à en cours set dateStart=getdate()
--de en cours à terminee set dateEnd=getdate()

--constraint
--on peut passer au status annule que depuis les status cree et en cours
--on ne peut enchérir sur un article uniquement quand son status est en cours

USE [BID]
GO

/****** Object:  Table [dbo].[BID_ITEM_CATEGORIES]    Script Date: 18/11/2021 15:40:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
DROP TABLE BIDS;
DROP TABLE USERS;
DROP TABLE BID_ITEMS;
DROP TABLE BID_ITEM_PICKUP_ADDRESS;
DROP TABLE BID_ITEM_CATEGORIES;
*/

CREATE TABLE [dbo].[BID_ITEM_CATEGORIES](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](30) NOT NULL,
 CONSTRAINT [PK_BID_ITEM_CATEGORIES] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BID_ITEM_PICKUP_ADDRESS]    Script Date: 18/11/2021 15:40:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BID_ITEM_PICKUP_ADDRESS](
	[itemId] [int] NOT NULL,
	[street] [varchar](30) NOT NULL,
	[zipCode] [varchar](10) NOT NULL,
	[town] [varchar](40) NOT NULL,
 CONSTRAINT [PK_BID_ITEM_PICKUP_ADDRESS] PRIMARY KEY CLUSTERED 
(
	[itemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BID_ITEMS]    Script Date: 18/11/2021 15:40:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BID_ITEMS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](30) NOT NULL,
	[description] [varchar](300) NOT NULL,
	[dateStart] [datetime] NOT NULL,
	[dateEnd] [datetime] NOT NULL,
	[priceSeller] [int] NULL,
	[priceBuyer] [int] NULL,
	[userIdSeller] [int] NOT NULL,
	[userIdBuyer] [int] NULL,
	[cateId] [int] NOT NULL,
	[status] [varchar](10) NOT NULL, 
 CONSTRAINT [PK_BID_ITEMS] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BIDS]    Script Date: 18/11/2021 15:40:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BIDS](
	[userIdBuyer] [int] NOT NULL,
	[itemId] [int] NOT NULL,
	[dateCreated] [datetime] NOT NULL,
	[amount] [int] NOT NULL,	
 CONSTRAINT [PK_BIDS] PRIMARY KEY CLUSTERED 
(
	[userIdBuyer] ASC,
	[itemId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[USERS]    Script Date: 18/11/2021 15:40:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USERS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[pseudo] [varchar](30) NOT NULL,
	[name] [varchar](30) NOT NULL,
	[firstName] [varchar](30) NOT NULL,
	[email] [varchar](40) NOT NULL,
	[telephone] [varchar](15) NULL,
	[street] [varchar](30) NOT NULL,
	[zipCode] [varchar](10) NOT NULL,
	[town] [varchar](40) NOT NULL,
	[pwd] [varchar](100) NOT NULL,
	[credit] [int] NOT NULL,
	[isAdmin] [bit] NOT NULL,
 CONSTRAINT [PK_USERS] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BID_ITEMS] ADD  DEFAULT (getdate()) FOR [dateStart]
GO
ALTER TABLE [dbo].[BID_ITEMS] ADD  DEFAULT (getdate()+(90)) FOR [dateEnd]
GO
ALTER TABLE [dbo].[BIDS] ADD  DEFAULT (getdate()) FOR [dateCreated]
GO
ALTER TABLE [dbo].[BIDS] ADD  DEFAULT ((0)) FOR [amount]
GO
ALTER TABLE [dbo].[USERS] ADD  DEFAULT ((0)) FOR [isAdmin]
GO
ALTER TABLE [dbo].[BID_ITEM_PICKUP_ADDRESS]  WITH CHECK ADD  CONSTRAINT [FK_BID_ITEM_PICKUP_ADDRESS_BID_ITEMS] FOREIGN KEY([itemId])
REFERENCES [dbo].[BID_ITEMS] ([id])
GO
ALTER TABLE [dbo].[BID_ITEM_PICKUP_ADDRESS] CHECK CONSTRAINT [FK_BID_ITEM_PICKUP_ADDRESS_BID_ITEMS]
GO
ALTER TABLE [dbo].[BID_ITEMS]  WITH CHECK ADD  CONSTRAINT [FK_BID_ITEMS_BID_ITEM_CATEGORIES] FOREIGN KEY([cateId])
REFERENCES [dbo].[BID_ITEM_CATEGORIES] ([id])
GO
ALTER TABLE [dbo].[BID_ITEMS] CHECK CONSTRAINT [FK_BID_ITEMS_BID_ITEM_CATEGORIES]
GO
ALTER TABLE [dbo].[BID_ITEMS]  WITH CHECK ADD  CONSTRAINT [FK_BID_ITEMS_USERS_SELLER] FOREIGN KEY([userIdSeller])
REFERENCES [dbo].[USERS] ([id])
GO
ALTER TABLE [dbo].[BID_ITEMS] CHECK CONSTRAINT [FK_BID_ITEMS_USERS_SELLER]
GO
ALTER TABLE [dbo].[BID_ITEMS]  WITH CHECK ADD  CONSTRAINT [FK_BID_ITEMS_USERS_BUYER] FOREIGN KEY([userIdBuyer])
REFERENCES [dbo].[USERS] ([id])
GO
ALTER TABLE [dbo].[BID_ITEMS] CHECK CONSTRAINT [FK_BID_ITEMS_USERS_BUYER]
GO
ALTER TABLE [dbo].[BIDS]  WITH CHECK ADD  CONSTRAINT [FK_BIDS_BID_ITEMS] FOREIGN KEY([itemId])
REFERENCES [dbo].[BID_ITEMS] ([id])
GO
ALTER TABLE [dbo].[BIDS] CHECK CONSTRAINT [FK_BIDS_BID_ITEMS]
GO
ALTER TABLE [dbo].[BIDS]  WITH CHECK ADD  CONSTRAINT [FK_BIDS_USERS] FOREIGN KEY([userIdBuyer])
REFERENCES [dbo].[USERS] ([id])
GO
ALTER TABLE [dbo].[BIDS] CHECK CONSTRAINT [FK_BIDS_USERS]
GO
USE [master]
GO
ALTER DATABASE [BID] SET  READ_WRITE 
GO
