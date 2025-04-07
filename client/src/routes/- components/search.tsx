import axios from "axios"
import { useQuery } from '@tanstack/react-query'
const url = "http://localhost:8080"
// import { useState } from "react"


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

const fetchGames = async (query: string) => {
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    query = "a"
    console.info(`${url}/search?query=metroid`)
    await new Promise((r) => setTimeout(r, 5000))
    return axios
    .get<{ results: Game[] }>(`${url}/search?query=metroid`)
    .then((response) => response.data.results)
    
    .catch((err) => {
        console.error('Error fetching games:', err);
        throw err
    })

}
export default function Search() {
    // const [results, setResults] = useState<Game[]>([]);
    
    const {status, data, error} = useQuery({ queryKey: ['games'], queryFn: async () => fetchGames("metroid")
    
    })

    if (status === "pending") {
        return <span>Loading...</span>
    }

    if (status === 'error') {
        return <span>Error: {error.message}</span>
      }
    
      return (
        <div>
            <h2>Search Results</h2>
            {data.length === 0 ? (
                <p>No results found.</p>
            ) : (
                <ul>
                    {data.map((game) => (
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