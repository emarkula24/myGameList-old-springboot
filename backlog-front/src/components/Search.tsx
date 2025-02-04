import React, { useState, useEffect} from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
const url = "http://localhost:8080"

interface Game {
    id: number;
    name: string;
    image?: {
        medium_url: string;
    };
    original_release_date?: string;
    platforms?: { abbreviation: string }[];
    deck?: string;
    site_detail_url: string;
}
export default function searchPage() {
    const location = useLocation();
    const [results, setResults] = useState<Game[]>([]);
    const query = new URLSearchParams(location.search).get('query');
    useEffect(() => {
        if (query && query.length > 0) {
            const fetchGames = async () => {
                try {
                    
                    const response = await axios.get<{results: Game[] }>(url + "/search", {
                        params: {query},
                    });
                    console.log(query)
                    console.log(response.data)
                    setResults(response.data.results);
                } catch (error) {
                console.error("Error fetching games", error)
                }
            };
            fetchGames();
        }
    }, [query]);

    console.log(query)
    
    return (
        <div>
            <h2>Search Results</h2>
            {results.length === 0 ? (
                <p>No results found.</p>
            ) : (
                <ul>
                    {results.map((game) => (
                        <li key={game.id} style={{ marginBottom: "20px", listStyle: "none" }}>
                            <h3>{game.name}</h3>
                            {game.image && (
                                <img
                                    src={game.image.medium_url}
                                    alt={game.name}
                                    style={{ width: "150px", borderRadius: "10px" }}
                                />
                            )}
                            <p><strong>Release Date:</strong> {game.original_release_date || "Unknown"}</p>
                            <p><strong>Platforms:</strong> {game.platforms?.map(p => p.abbreviation).join(", ") || "N/A"}</p>
                            <p>{game.deck}</p>
                            <a href={game.site_detail_url} target="_blank" rel="noopener noreferrer">
                                More Details
                            </a>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export {searchPage};