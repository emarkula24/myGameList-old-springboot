import axios from "axios"
import { keepPreviousData, useQuery } from '@tanstack/react-query'
const url = "http://localhost:8080"
import React, { useState } from "react"
import useDebounce from "../../hooks/useDebounce"

export default function SearchBar() {
    const [searchQuery, setSearchQuery] = useState("")
    const debouncedSearchQuery = useDebounce(searchQuery, 500);


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

    const fetchGames = async (searchQuery: string) => {
       
        const encodedSearchQuery = encodeURIComponent(searchQuery)
        console.info(`${url}/search?query=${encodedSearchQuery}`)
        await new Promise((r) => setTimeout(r, 5000))
        return axios
        .get<{ results: Game[] }>(`${url}/search?query=${searchQuery}`)
        .then((response) => response.data.results)
        .catch((err) => {
            console.error('Error fetching games:', err);
            throw err
        })
    
    }
    
    const { isFetched, isPending, isError, data, error } = useQuery({
        queryKey: ['games', debouncedSearchQuery],
        queryFn: async () => fetchGames(debouncedSearchQuery),
        enabled: !!searchQuery,
        placeholderData: keepPreviousData,
      });

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
        setSearchQuery(event.currentTarget.value)
    }

    
    return (
        <div>
          <label>
            Search: 
            <input 
              type="text"
              name="searchInput"
              value={searchQuery}
              onChange={handleInputChange}
            />
          </label>
          {isPending   ? (
            <div>Loading..</div>
          ): isError ? (
          <div>Error: {error.message}</div>
        ): isFetched && data &&
          (
            <ul>
              {data.map((game) => (
                <li key={game.id}>{game.name}</li>
              ))}
            </ul>
          )}
        </div>
      );
    }