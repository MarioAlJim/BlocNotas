use BlogNotas;
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE dbo.SPI_RegisterUser
	@nombre NVARCHAR(100),
	@apellidos NVARCHAR(100),
	@celular NVARCHAR(50),
	@contrasena NVARCHAR(50),
	@otp NVARCHAR(6),
	@result INT OUTPUT  
AS
BEGIN

	IF NOT EXISTS (SELECT celular FROM usuario WHERE usuario.celular = @celular) 
	BEGIN
		INSERT INTO [dbo].[usuario]
			   (nombres
			   ,apellidos
			   ,tiempo_registro
			   ,activo
			   ,celular
			   ,contrasena
			   ,otp)
		 VALUES
			   (@nombre
			   ,@apellidos
			   ,GETDATE()
			   ,0
			   ,@celular
			   ,@contrasena
			   ,@otp)
		SET @result = 1
	END
	ELSE 
	BEGIN
		SET @result = 0;
	END

END
GO
