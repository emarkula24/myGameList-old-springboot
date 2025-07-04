import { createRootRoute, Outlet } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/react-router-devtools'
import Header from './- components/Header'

export const Route = createRootRoute({
  component: () => (
    <>
      <div>

        < Header />
      </div>
      <hr />
      <Outlet />
      <TanStackRouterDevtools />
    </>
  ),
  notFoundComponent: () => <div>404 Not Found</div>,
})