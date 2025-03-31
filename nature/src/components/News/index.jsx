import { useEffect, useState } from "react";

export default function News() {
    const [news, setNews] = useState([]); // Estado para almacenar las noticias
    const [loading, setLoading] = useState(true); // Estado de carga

    useEffect(() => {
        const fetchNews = async () => {
            const url = 'https://climate-news-feed.p.rapidapi.com/';
            const options = {
                method: 'GET',
                headers: {
                    'x-rapidapi-key': '142eacc64dmsh675b7f473e8df77p1c8dd8jsn01f8fa70ad9d',
                    'x-rapidapi-host': 'climate-news-feed.p.rapidapi.com'
                }
            };

            try {
                const response = await fetch(url, options);
                const result = await response.json(); // Convertir la respuesta a JSON
                setNews(result.articles || []); // Asignar los artículos al estado
            } catch (error) {
                console.error("Error al obtener noticias:", error);
            } finally {
                setLoading(false); // Cambiar estado de carga
            }
        };

        fetchNews();
    }, []); // Se ejecuta solo una vez al montar el componente

    return (
        <div>
            <h2>Últimas Noticias Climáticas</h2>

            {loading && <p>Cargando noticias...</p>}

            <div>
                {news.length > 0 ? (
                    news.map((article, index) => (
                        <div key={index} style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "10px" }}>
                            <h3>{article.title}</h3>
                            <p>
                                <strong>Fuente:</strong> {article.source?.name || "Desconocida"}
                            </p>
                            <p>
                                <strong>Publicado:</strong> {article.published ? new Date(article.published).toLocaleDateString() : "Fecha no disponible"}
                            </p>
                            {article.thumbnail && (
                                <a href={article.url} target="_blank" rel="noopener noreferrer">
                                    <img src={article.thumbnail} alt={article.title} style={{ width: "100%", maxWidth: "400px" }} />
                                </a>
                            )}
                            <p>
                                <a href={article.url} target="_blank" rel="noopener noreferrer">Leer más</a>
                            </p>
                        </div>
                    ))
                ) : (
                    !loading && <p>No hay noticias disponibles</p>
                )}
            </div>
        </div>
    );
}
