cd wedding-process
git pull
cd ..
git pull
docker stop backend
docker stop frontend
docker rm backend
docker rm frontend
docker rmi questionneire_backend
docker rmi questionneire_frontend
docker compose up