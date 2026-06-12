#!/bin/bash

echo "🚀 Iniciando Blog Security Application..."

# Verificar si MySQL está corriendo
if ! pgrep -x "mysqld" > /dev/null; then
    echo "⚠️  MySQL no está corriendo. Por favor, inicia MySQL primero."
    echo "   En macOS: brew services start mysql"
    exit 1
fi

# Crear base de datos si no existe
echo "📦 Verificando base de datos..."
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS blog_db;"

# Compilar y ejecutar
echo "🔨 Compilando proyecto..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa!"
    echo "🌐 Iniciando servidor en http://localhost:8080"
    echo ""
    echo "Credenciales de Admin:"
    echo "  Usuario: admin"
    echo "  Contraseña: admin123"
    echo ""
    mvn spring-boot:run
else
    echo "❌ Error en la compilación"
    exit 1
fi

